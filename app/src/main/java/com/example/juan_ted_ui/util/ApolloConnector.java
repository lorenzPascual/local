package com.example.juan_ted_ui.util;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class ApolloConnector {

    private static final String BASE_URL = "http://167.172.155.29/graphql";

    public static ApolloClient setupApollo(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build();
    }

}
