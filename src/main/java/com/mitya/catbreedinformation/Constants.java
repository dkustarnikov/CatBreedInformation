package com.mitya.catbreedinformation;

import com.mitya.catbreedinformation.model.Cat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static final String API_KEY = "da394036-caab-40f9-892b-b9063c1becc7";
    public static Map<String, Cat> cats = new HashMap();
    public static List<String> breedNamesOnly = new ArrayList<>();

    public static final String ARG_NAME = "name";
    public static final String ARG_ORIGIN = "origin";
    public static final String ARG_TEMPERAMENT = "temperament";

}
