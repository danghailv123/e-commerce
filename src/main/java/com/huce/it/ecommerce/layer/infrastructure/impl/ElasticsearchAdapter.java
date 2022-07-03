package com.huce.it.ecommerce.layer.infrastructure.impl;

import com.huce.it.ecommerce.layer.infrastructure.IElasticsearchAdapter;
import com.huce.it.ecommerce.layer.infrastructure.config.ElasticConfig;
import com.huce.it.ecommerce.unitity.http.HostAndPort;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchAdapter implements IElasticsearchAdapter {
    private final RestHighLevelClient client;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public ElasticsearchAdapter(ElasticConfig config) {
        HttpHost[] httpHosts = new HttpHost[config.getHosts().size()];

        int i = 0;
        for (HostAndPort hp : config.getHosts()) {
            httpHosts[i++] = new HttpHost(hp.getHost(), hp.getPort(), "http");
        }

        RestClientBuilder clientBuilder = RestClient.builder(httpHosts);

        if (config.isSecurity()) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, config.getAuthentication());
            clientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder ->
                    httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }
        this.client = new RestHighLevelClient(clientBuilder);
    }

    @Override
    public RestHighLevelClient getClient() {
        return client;
    }
}
