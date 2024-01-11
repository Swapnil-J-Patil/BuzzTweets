package com.example.tweet.api

import com.example.tweet.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {

    @GET("/v3/b/659b9ee8266cfc3fde740c51?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path")category: String): Response<List<TweetListItem>>
    //asynchronous calls to getTweets

    @GET("/v3/b/659b9ee8266cfc3fde740c51?meta=false")
    @Headers("X-JSON-Path: tweets..category")//static parameter
    suspend fun getCategories():Response<List<String>>
}