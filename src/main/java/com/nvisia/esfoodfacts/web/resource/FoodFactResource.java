package com.nvisia.esfoodfacts.web.resource;

import com.nvisia.esfoodfacts.service.FoodFactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/foodfact")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FoodFactResource {

    private final FoodFactService foodFactService;

    @RequestMapping(path = "/load", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity reloadFoodFactData() {
        return new ResponseEntity(HttpStatus.valueOf(
            foodFactService.loadData()));
    }
}