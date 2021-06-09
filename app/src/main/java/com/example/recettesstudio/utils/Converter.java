package com.example.recettesstudio.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Converter {

    @TypeConverter
    public static ArrayList<String> toStrings(String value) {
        return new Gson().fromJson(value, new TypeToken<ArrayList<String>>() {
        }.getType());
    }
    @TypeConverter
    public static String fromStrings(ArrayList<String> value) {
        return value == null ? null : new Gson().toJson(new ArrayList<>(value));
    }

}
