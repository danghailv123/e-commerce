package com.huce.it.ecommerce.layer.infrastructure;

import org.elasticsearch.client.RestHighLevelClient;

public interface IElasticsearchAdapter {
    RestHighLevelClient getClient();
}
