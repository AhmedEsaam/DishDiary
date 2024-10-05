package com.example.dishdiary.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("idIngredient")
    String idIngredient;

    @SerializedName("strIngredient")
    String strIngredient;

    @SerializedName("strDescription")
    String strDescription;

    @SerializedName("strType")
    String strType;

    public Ingredient(String idIngredient, String strIngredient, String strDescription, String strType) {
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
        this.strDescription = strDescription;
        this.strType = strType;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }
    public String getIdIngredient() {
        return idIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }
    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }
    public String getStrDescription() {
        return strDescription;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }
    public String getStrType() {
        return strType;
    }

}