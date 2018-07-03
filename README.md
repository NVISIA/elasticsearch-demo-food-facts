# food-facts
Demo project for NVISIA Elasticsearch demonstration. Data is sourced from Open Food Facts dataset: https://www.kaggle.com/openfoodfacts/world-food-facts. 

# Project Creation

I used http://start.spring.io to generate the initial project scaffolding. 

I added the following dependencies:

* DevTools
* Lombok
* Web
* Thymeleaf
* JPA
* Liquibase
* Redis
* Elasticsearch
* Kafka
* Kafka Streams
* Actuator

# Execution

The default command to run is:
 
``` 
./gradlew bootRun
```

# Running Elasticsearch

There are many options for running Elasticsearch. In this project, I've included a file src/main/docker/elasticsearch.yml that defines an elasticsearch service. This can be executed using docker-compose to remove the need for custom installation or AWS registration. Elasticsearch can be resource intensive, so its recommended that you increase the resources available to Docker to at least 4-6GB. The current service definition gives ES a heap size of 2GB. 

## Start

```
docker-compose -f src/main/docker/elasticsearch.yml up -d
```

If verification fails, you may want to run without -d to see the log and any issue that might have occurred during startup. Most commonly failures in startup are related to memory. 

## Stop

```
docker-compose -f src/main/docker/elasticsearch.yml down
```

## Verify

```
curl http://127.0.0.1:9200/_cat/health
```

# Running PostgreSQL

Starting:

```
docker-compose -f src/main/docker/postgresql.yml up -d
```

Stopping:

```
docker-compose -f src/main/docker/postgresql.yml down
```

