package com.nvisia.esfoodfacts.web.resource;

import com.nvisia.esfoodfacts.repository.ESRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/index")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ESIndexResource {

    private static final Logger log = LoggerFactory.getLogger(ESIndexResource.class);

    private final ESRepository esRepository;

    @RequestMapping(method=RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity createIndex() {
        return new ResponseEntity(
            HttpStatus.valueOf(esRepository.createFoodProductIndex()));
    }

    @RequestMapping(method=RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity deleteIndex() {
        return new ResponseEntity(
            HttpStatus.valueOf(esRepository.deleteFoodProductIndex()));
    }

    @RequestMapping(path = "/reload", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity reloadIndex() {
        int deleteIndexStatus = esRepository.deleteFoodProductIndex();
        int createIndexStatus = esRepository.createFoodProductIndex();
        int createMappingStatus = esRepository.createFoodProductMapping();
        if (deleteIndexStatus != 200 || createIndexStatus != 200 ||
            createMappingStatus != 200) {
            return new ResponseEntity(HttpStatus.valueOf(500));
        } else {
            return new ResponseEntity(HttpStatus.valueOf(200));
        }
    }

}
