package com.huce.it.ecommerce.layer.infrastructure.impl.read;

import com.huce.it.ecommerce.layer.infrastructure.config.ElasticConfig;
import com.huce.it.ecommerce.layer.infrastructure.impl.ElasticsearchAdapter;
import com.huce.it.ecommerce.unitity.Pair;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ElasticsearchReadAdapter extends ElasticsearchAdapter {

    public ElasticsearchReadAdapter(ElasticConfig config) {
        super(config);
    }

    public SearchResponse search(String index, BoolQueryBuilder bqb, List<Pair<String, SortOrder>> sorts, int limit, int page) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(bqb);
        searchSourceBuilder.size(limit);
        searchSourceBuilder.from((page) * limit);
        if (sorts != null) {
            for (Pair<String, SortOrder> sort : sorts) {
                searchSourceBuilder.sort(sort.getKey(), sort.getValue());
            }
        }
        searchSourceBuilder.minScore(0.001F);
        searchRequest.source(searchSourceBuilder);

        return getClient().search(searchRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse searchAll(String index, BoolQueryBuilder bqb, List<Pair<String, SortOrder>> sorts) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(bqb);
        searchSourceBuilder.size(100);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueSeconds(1L));
        if (sorts != null) {
            for (Pair<String, SortOrder> sort : sorts) {
                searchSourceBuilder.sort(sort.getKey(), sort.getValue());
            }
        }
        searchSourceBuilder.minScore(1.0F);
        searchRequest.source(searchSourceBuilder);

        return getClient().search(searchRequest, RequestOptions.DEFAULT);
    }

    public Map<String, Object> findById (String index , String id) throws IOException {
        GetRequest getRequest = new GetRequest(index,id);

        GetResponse getResponse = getClient().get(getRequest, RequestOptions.DEFAULT);

        return getResponse.getSource();
    }

    public List<SearchHit> searchScrollQuery(String index,BoolQueryBuilder queryBuilder) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (queryBuilder != null) searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(50);
        searchSourceBuilder.minScore(0.0001F);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueSeconds(1L));
        SearchResponse searchResponse = getClient().search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        List<SearchHit> allHit = new ArrayList<>();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        while (searchHits !=null && searchHits.length>0){
            allHit.addAll(Arrays.asList(searchHits));
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(TimeValue.timeValueSeconds(1L));
            SearchResponse searchScrollResponse = getClient().scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchScrollResponse.getScrollId();
            searchHits =searchScrollResponse.getHits().getHits();
        }
        return allHit;
    }
}

