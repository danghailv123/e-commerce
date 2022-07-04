package com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.constant.SearchFields;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.IElasticProductGroupDao;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.IElasticTrademarkDao;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.TrademarkDto;
import com.huce.it.ecommerce.layer.infrastructure.impl.read.ElasticsearchReadAdapter;
import com.huce.it.ecommerce.layer.infrastructure.impl.write.ElasticsearchWriteAdapter;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ElasticTrademarkDao implements IElasticTrademarkDao {

    private final ElasticsearchReadAdapter elasticsearchReadAdapter;
    private final ElasticsearchWriteAdapter elasticsearchWriteAdapter;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String INDEX;

    public ElasticTrademarkDao(ElasticsearchReadAdapter elasticsearchReadAdapter, ElasticsearchWriteAdapter elasticsearchWriteAdapter, @Value("${index.trademark}") String index) {
        this.elasticsearchReadAdapter = elasticsearchReadAdapter;
        this.elasticsearchWriteAdapter = elasticsearchWriteAdapter;
        INDEX = index;
    }

    @Override
    public TrademarkDto getProfile(String id) {

        try {
            GetRequest getRequest = new GetRequest(INDEX, id);
            GetResponse getResponse = elasticsearchReadAdapter.getClient().get(getRequest, RequestOptions.DEFAULT);
            if (getResponse != null) {
                String source = getResponse.getSourceAsString();
                if (source != null) {
                    return Constants.SERIALIZER.readValue(source, TrademarkDto.class);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @Override
    public TrademarkDto save(TrademarkDto trademarkDto) throws IOException {
        String data = Constants.SERIALIZER.writeValueAsString(trademarkDto);
        elasticsearchWriteAdapter.getClient().index(new IndexRequest(INDEX)
                .id(trademarkDto.getId().toString())
                .source(data, XContentType.JSON), RequestOptions.DEFAULT);
        return Constants.SERIALIZER.readValue(data, TrademarkDto.class);
    }

    @Override
    public ResultResponse search(String keyWord, Integer limit, Integer page) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery(SearchFields.ENTRY , SearchFields.TRADEMARK));
        boolQueryBuilder.must(QueryBuilders.matchQuery(SearchFields.STATUS , 1));
        if (!Strings.isNullOrEmpty(keyWord))
        {
            boolQueryBuilder.must(QueryBuilders.queryStringQuery("*"+keyWord+"*").field(SearchFields.NAME));
        }
        ResultResponse<TrademarkDto> resultResponse = new ResultResponse<>();
        List<TrademarkDto> data = new ArrayList<>();

        try {
            SearchResponse searchResponse = elasticsearchReadAdapter.getAll(INDEX, boolQueryBuilder, SearchFields.ID, limit, page);
            resultResponse.setLimit(limit);
            resultResponse.setPage(page);
            resultResponse.setTotal(searchResponse.getHits().getTotalHits().value);
            for (SearchHit hit : searchResponse.getHits().getHits()) {
                TrademarkDto fileUpload = Constants.SERIALIZER.readValue(hit.getSourceAsString(), TrademarkDto.class);
                data.add(fileUpload);
            }
            resultResponse.setData(data);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return resultResponse;

    }
}
