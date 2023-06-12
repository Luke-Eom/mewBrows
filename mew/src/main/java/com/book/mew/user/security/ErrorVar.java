package com.book.mew.user.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.MediaType;

public class ErrorVar {
    public static final Gson GSON = new GsonBuilder().create();
    public static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON;

}
