package com.nvisia.esfoodfacts.service;

import com.nvisia.esfoodfacts.entity.FoodFact;
import com.nvisia.esfoodfacts.entity.NameReference;
import com.nvisia.esfoodfacts.repository.ESRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FoodFactService {

    private static final Logger log = LoggerFactory.getLogger(FoodFactService.class);
    private final ESRepository esRepository;

    public int loadData() {
        try {
            ForkJoinPool fjp = new ForkJoinPool(10);
            fjp.submit(() -> {
                        try (FileReader reader = new FileReader(
                            new ClassPathResource("data/openfoodfacts_search.csv").getFile())) {
                            new CSVParser(reader, CSVFormat.EXCEL.withFirstRecordAsHeader()).
                                getRecords().
                                parallelStream().
                                forEach(record -> {
                                    FoodFact foodFact = new FoodFact();
                                    foodFact.setCode(record.get(0));
                                    foodFact.setUrl(record.get(1));
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    foodFact.setCreatedDate(
                                        dateFormat.format(new Date(Long.parseLong(record.get(3)))));
                                    foodFact.setLastModifiedDate(
                                        dateFormat.format(new Date(Long.parseLong(record.get(4)))));
                                    foodFact.setProductName((record.get(5) != null) ? record.get(5) : "");
                                    foodFact.setGenericName((record.get(6) != null) ? record.get(6) : "");
                                    foodFact.setQuantity((record.get(7) != null) ? record.get(7) : "");
                                    foodFact.setPackaging(buildNameRefs(record.get(9)));
                                    foodFact.setBrands(buildNameRefs(record.get(11)));
                                    foodFact.setCategories(buildNameRefs(record.get(13)));
                                    foodFact.setLabels(buildNameRefs(record.get(15)));
                                    foodFact.setManufacturingPlaces(buildNameRefs(record.get(19)));
                                    foodFact.setPurchasePlaces(buildNameRefs(record.get(24)));
                                    foodFact.setStores(buildNameRefs(record.get(25)));
                                    foodFact.setCountries(buildNameRefs(record.get(26)));
                                    foodFact.setIngredients((record.get(27) != null) ? record.get(27) : "");
                                    foodFact.setAllergens(buildNameRefs(record.get(29)));
                                    foodFact.setServingSize((record.get(32) != null) ? record.get(32): "");
                                    foodFact.setAdditives(buildNameRefsForAdditives(record.get(35)));
                                    foodFact.setCategoryGroup1((record.get(43) != null) ? record.get(43) : "");
                                    foodFact.setCategoryGroup2((record.get(44) != null) ? record.get(44) : "");
                                    foodFact.setMainCategory((record.get(45) != null) ?
                                        StringUtils.substringAfter(record.get(45), ":") : "");
                                    foodFact.setEnergy100g((record.get(54) != null) ?
                                        Double.parseDouble(record.get(54)) : 0);
                                    foodFact.setEnergyFromFat100g((record.get(55) != null) ?
                                        Double.parseDouble(record.get(55)) : 0);
                                    foodFact.setFat100g((record.get(56) != null) ?
                                        Double.parseDouble(record.get(56)) : 0);
                                    foodFact.setSaturatedFat100g((record.get(57) != null) ?
                                        Double.parseDouble(record.get(57)) : 0);
                                    foodFact.setMonounsaturatedFat100g((record.get(72) != null) ?
                                        Double.parseDouble(record.get(72)) : 0);
                                    foodFact.setPolyunsaturatedFat100g((record.get(73) != null) ?
                                        Double.parseDouble(record.get(73)) : 0);
                                    foodFact.setOmega3Fat100g((record.get(74) != null) ?
                                        Double.parseDouble(record.get(74)) : 0);
                                    foodFact.setTransFat100g((record.get(90) != null) ?
                                        Double.parseDouble(record.get(90)) : 0);
                                    foodFact.setCholesterol100g((record.get(91) != null) ?
                                        Double.parseDouble(record.get(91)) : 0);
                                    foodFact.setCarbohydrates100g((record.get(92) != null) ?
                                        Double.parseDouble(record.get(92)) : 0);
                                    foodFact.setSugars100g((record.get(93) != null) ?
                                        Double.parseDouble(record.get(93)) : 0);
                                    foodFact.setFiber100g((record.get(102) != null) ?
                                        Double.parseDouble(record.get(102)) : 0);
                                    foodFact.setProteins100g((record.get(103) != null) ?
                                        Double.parseDouble(record.get(103)) : 0);
                                    foodFact.setSalt100g((record.get(107) != null) ?
                                        Double.parseDouble(record.get(107)) : 0);
                                    foodFact.setSodium100g((record.get(108) != null) ?
                                        Double.parseDouble(record.get(108)) : 0);
                                    foodFact.setAlcohol100g((record.get(109) != null) ?
                                        Double.parseDouble(record.get(109)) : 0);
                                    foodFact.setVitamina100g((record.get(110) != null) ?
                                        Double.parseDouble(record.get(110)) : 0);
                                    foodFact.setVitamind100g((record.get(112) != null) ?
                                        Double.parseDouble(record.get(112)) : 0);
                                    foodFact.setVitamine100g((record.get(113) != null) ?
                                        Double.parseDouble(record.get(113)) : 0);
                                    foodFact.setVitamink100g((record.get(114) != null) ?
                                        Double.parseDouble(record.get(114)) : 0);
                                    foodFact.setVitaminc100g((record.get(115) != null) ?
                                        Double.parseDouble(record.get(115)) : 0);
                                    foodFact.setVitaminb1100g((record.get(116) != null) ?
                                        Double.parseDouble(record.get(116)) : 0);
                                    foodFact.setVitaminb2100g((record.get(117) != null) ?
                                        Double.parseDouble(record.get(117)) : 0);
                                    foodFact.setVitaminpp100g((record.get(118) != null) ?
                                        Double.parseDouble(record.get(118)) : 0);
                                    foodFact.setVitaminb6100g((record.get(119) != null) ?
                                        Double.parseDouble(record.get(119)) : 0);
                                    foodFact.setVitaminb9100g((record.get(120) != null) ?
                                        Double.parseDouble(record.get(120)) : 0);
                                    foodFact.setFolates100g((record.get(121) != null) ?
                                        Double.parseDouble(record.get(121)) : 0);
                                    foodFact.setVitaminb12100g((record.get(122) != null) ?
                                        Double.parseDouble(record.get(122)) : 0);
                                    foodFact.setBiotin100g((record.get(123) != null) ?
                                        Double.parseDouble(record.get(123)) : 0);
                                    foodFact.setPantothenicAcid100g((record.get(124) != null) ?
                                        Double.parseDouble(record.get(124)) : 0);
                                    foodFact.setSilica100g((record.get(125) != null) ?
                                        Double.parseDouble(record.get(125)) : 0);
                                    foodFact.setBicarbonate100g((record.get(126) != null) ?
                                        Double.parseDouble(record.get(126)) : 0);
                                    foodFact.setPotassium100g((record.get(127) != null) ?
                                        Double.parseDouble(record.get(127)) : 0);
                                    foodFact.setChloride100g((record.get(128) != null) ?
                                        Double.parseDouble(record.get(128)) : 0);
                                    foodFact.setCalcium100g((record.get(129) != null) ?
                                        Double.parseDouble(record.get(129)) : 0);
                                    foodFact.setPhosphorus100g((record.get(130) != null) ?
                                        Double.parseDouble(record.get(130)) : 0);
                                    foodFact.setIron100g((record.get(131) != null) ?
                                        Double.parseDouble(record.get(131)) : 0);
                                    foodFact.setMagnesium100g((record.get(132) != null) ?
                                        Double.parseDouble(record.get(132)) : 0);
                                    foodFact.setZinc100g((record.get(133) != null) ?
                                        Double.parseDouble(record.get(133)) : 0);
                                    foodFact.setCopper100g((record.get(134) != null) ?
                                        Double.parseDouble(record.get(134)) : 0);
                                    foodFact.setManganese100g((record.get(135) != null) ?
                                        Double.parseDouble(record.get(135)) : 0);
                                    try {
                                        esRepository.createFoodFact(foodFact);
                                    } catch (Exception e) {
                                        log.error("An error occurred while processing a food fact: " + e.getMessage(), e);
                                    }
                                });
                        } catch (Exception e) {
                            log.error("An error occurred: " + e.getMessage(), e);
                        }
                    }
            ).get();
            return HttpStatus.OK.value();
        } catch (Exception e) {
            log.error("An error occurred during data load: " + e.getMessage(), e);
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    protected List<NameReference> buildNameRefs(String record) {
        List<NameReference> nameList = new ArrayList<>();
        if (record != null) {
            nameList = Stream.of(record.split(",")).
                    map(name -> {
                        NameReference nameRef = new NameReference();
                        nameRef.setName(name);
                        return nameRef;
                    }).
                    collect(Collectors.toList());
        }
        return nameList;
    }

    protected List<NameReference> buildNameRefsForAdditives(String record) {
        List<NameReference> nameList = new ArrayList<>();
        if (record != null) {
            Arrays.stream(StringUtils.substringsBetween(record, "[", "->")).
                forEach(additive -> {
                    NameReference ref = new NameReference();
                    ref.setName(StringUtils.trim(additive));
                    nameList.add(ref);
                });
        }
        return nameList;
    }
}
