package com.huce.it.ecommerce.layer.infrastructure.impl.write;

import com.huce.it.ecommerce.layer.infrastructure.config.ElasticConfig;
import com.huce.it.ecommerce.layer.infrastructure.impl.ElasticsearchAdapter;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ElasticsearchWriteAdapter extends ElasticsearchAdapter {
    public ElasticsearchWriteAdapter(ElasticConfig config) {
        super(config);
    }

    public boolean createIndex(String index) {
        try {
            GetIndexRequest requestExist = new GetIndexRequest(index);

            if (!getClient().indices().exists(requestExist, RequestOptions.DEFAULT)) {
                CreateIndexRequest requestCreateIndex = new CreateIndexRequest(index);
                getClient().indices().create(requestCreateIndex, RequestOptions.DEFAULT);
                logger.info("Create Index " + index + " success");
            }else {
                logger.info("Index " + index + " exist");
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
