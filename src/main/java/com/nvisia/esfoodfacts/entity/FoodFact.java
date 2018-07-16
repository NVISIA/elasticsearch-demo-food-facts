package com.nvisia.esfoodfacts.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class FoodFact implements Serializable {
    private static final long serialVersionUID = -1;

    private String code;
    private String url;
    private String createdDate;
    private String lastModifiedDate;
    private String productName;
    private String genericName;
    private String quantity;
    private List<NameReference> packaging;
    private List<NameReference> brands;
    private List<NameReference> categories;
    private List<NameReference> labels;
    private List<NameReference> manufacturingPlaces;
    private List<NameReference> purchasePlaces;
    private List<NameReference> stores;
    private List<NameReference> countries;
    private String ingredients;
    private List<NameReference> allergens;
    private String servingSize;
    private List<NameReference> additives;
    private String categoryGroup1;
    private String categoryGroup2;
    private String mainCategory;
    private Double energy100g;
    private Double energyFromFat100g;
    private Double fat100g;
    private Double saturatedFat100g;
    private Double monounsaturatedFat100g;
    private Double polyunsaturatedFat100g;
    private Double omega3Fat100g;
    private Double transFat100g;
    private Double cholesterol100g;
    private Double carbohydrates100g;
    private Double sugars100g;
    private Double fiber100g;
    private Double proteins100g;
    private Double salt100g;
    private Double sodium100g;
    private Double alcohol100g;
    private Double vitamina100g;
    private Double vitamind100g;
    private Double vitamine100g;
    private Double vitamink100g;
    private Double vitaminc100g;
    private Double vitaminb1100g;
    private Double vitaminb2100g;
    private Double vitaminpp100g;
    private Double vitaminb6100g;
    private Double vitaminb9100g;
    private Double folates100g;
    private Double vitaminb12100g;
    private Double biotin100g;
    private Double pantothenicAcid100g;
    private Double silica100g;
    private Double bicarbonate100g;
    private Double potassium100g;
    private Double chloride100g;
    private Double calcium100g;
    private Double phosphorus100g;
    private Double iron100g;
    private Double magnesium100g;
    private Double zinc100g;
    private Double copper100g;
    private Double manganese100g;
}
