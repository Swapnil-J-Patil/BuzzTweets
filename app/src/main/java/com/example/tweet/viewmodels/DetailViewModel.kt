package com.example.tweet.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweet.models.TweetListItem
import com.example.tweet.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository:TweetRepository,
    private val savedStateHandle: SavedStateHandle):ViewModel() {

    //this will fetch data using api call and we will pass it to views
    val tweets:StateFlow<List<TweetListItem>>
        get() = repository.tweets


    //Coroutine launch
    init {
        viewModelScope.launch {
            val category=savedStateHandle.get<String>("category")?: "motivation"//key
            repository.getTweets(category)
        }
    }
}