package com.example.tweet.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweet.R
import com.example.tweet.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(onClick:(category:String)->Unit) {

    val categoryViewModel:CategoryViewModel= hiltViewModel()
    val categories: State<List<String>> =categoryViewModel.category.collectAsState()

    if(categories.value.isEmpty())
    {
        Box(modifier = Modifier.fillMaxSize(1f)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        colorResource(id = R.color.color1),
                        colorResource(id = R.color.color2)
                    )
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading ...",
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center)
        }
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.color1),
                            colorResource(id = R.color.color2)
                        )
                    )
                )
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
            ) {
                items(categories.value.distinct())//list items
                {
                    CategoryItem(category = it, onClick)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(category: String,onClick:(category:String)->Unit) {
    Card(modifier = Modifier
        .padding(4.dp)
        .clickable {
            onClick(category)
        }
        .size(200.dp),
        elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp),
    ){
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tweet),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(0.dp, 18.dp, 0.dp, 0.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = category,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(0.dp, 8.dp, 0.dp, 8.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}



