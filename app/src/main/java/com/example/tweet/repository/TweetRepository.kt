package com.example.tweet.repository

import com.example.tweet.api.TweetsyAPI
import com.example.tweet.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {

    //observables
    private val _categories= MutableStateFlow<List<String>>(emptyList())// to avoid unauthorized data
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets= MutableStateFlow<List<TweetListItem>>(emptyList())// to avoid unauthorized data
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories()
    {
        val response = tweetsyAPI.getCategories()
        if(response.isSuccessful && response.body()!=null)
        {
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String)
    {
        val response = tweetsyAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if(response.isSuccessful && response.body()!=null)
        {
            _tweets.emit(response.body()!!)
        }
    }
}