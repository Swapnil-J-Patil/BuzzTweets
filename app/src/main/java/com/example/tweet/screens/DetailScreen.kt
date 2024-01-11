package com.example.tweet.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweet.R
import com.example.tweet.viewmodels.DetailViewModel


@Composable
fun DetailScreen() {
    val detailViewModel:DetailViewModel= hiltViewModel()
    val tweets=detailViewModel.tweets.collectAsState()
    LazyColumn(content = {
        items(tweets.value)
        {
            TweetListItem(tweet = it.text)
        }
    },
        modifier = Modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        colorResource(id = R.color.color1),
                        colorResource(id = R.color.color2)
                    )
                )
            ))
}
@Composable
fun TweetListItem(tweet: String) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Box (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(
                    Color.White
                )
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(10.dp, 12.dp)

            ) {
                Text(
                    text = tweet,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )
            }
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun preview() {
    DetailScreen()
}