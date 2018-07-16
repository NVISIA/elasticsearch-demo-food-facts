package com.nvisia.esfoodfacts.repository;

import com.google.gson.Gson;
import com.nvisia.esfoodfacts.entity.FoodFact;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.util.Collections;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ESRepository {

    private static final Logger log = LoggerFactory.getLogger(ESRepository.class);
    private final RestClient restClient;

    public int createFoodProductIndex() {
        try {
            Response response = restClient.performRequest(
        "PUT",
        "food_product_index_v1",
                Collections.emptyMap(),
                new NStringEntity(
                    new String(
                        Files.readAllBytes(
                            new ClassPathResource("es-schema/food_product_index_v1.json").getFile().toPath())),
                                ContentType.APPLICATION_JSON
                    ));
            return response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            log.error("Error occurred during creation: " + e.getMessage(), e);
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    public int deleteFoodProductIndex() {
        try {
            Response response = restClient.performRequest(
                    "DELETE",
                    "food_product_index_v1", Collections.emptyMap());
            return response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            log.error("Error occurred during deletion: " + e.getMessage(), e);
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    public int createFoodProductMapping() {
        try {
            Response response = restClient.performRequest(
                    "PUT",
                    "food_product_index_v1/_mapping/food_product_v1",
                    Collections.emptyMap(),
                    new NStringEntity(
                            new String(
                                Files.readAllBytes(
                                    new ClassPathResource("es-schema/food_product_v1.json").getFile().toPath())),
                            ContentType.APPLICATION_JSON
                    ));
            return response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            log.error("Error occurred during deletion: " + e.getMessage(), e);
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    public void createFoodFact(FoodFact foodFact) {
        try {
            Response response = restClient.performRequest(
                "POST",
                "food_product_index_v1/food_product_v1",
                    Collections.emptyMap(),
                    new NStringEntity(
                        new Gson().toJson(foodFact), ContentType.APPLICATION_JSON
                    )
            );
        } catch (Exception e) {
            log.error("An error occurred while saving a food fact to ES: " + e.getMessage(), e);
        }
    }
}
