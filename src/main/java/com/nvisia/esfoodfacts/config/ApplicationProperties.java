package com.nvisia.esfoodfacts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "foodfacts", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Elasticsearch elasticsearch = new Elasticsearch();

    public Elasticsearch getElasticsearch() {
        return elasticsearch;
    }

    public static class Elasticsearch {
        private String host;
        private int port = 9300;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }


}
