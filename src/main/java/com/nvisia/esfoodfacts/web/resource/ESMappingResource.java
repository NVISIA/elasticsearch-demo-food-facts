package com.nvisia.esfoodfacts.web.resource;

import com.nvisia.esfoodfacts.repository.ESRepository;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;

@Controller
@RequestMapping("/api/mapping")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ESMappingResource {

    private static final Logger log = LoggerFactory.getLogger(ESMappingResource.class);

    private final ESRepository esRepository;

    @RequestMapping(method=RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity createMapping() {
        return new ResponseEntity(HttpStatus.valueOf(esRepository.createFoodProductMapping()));
    }
}
