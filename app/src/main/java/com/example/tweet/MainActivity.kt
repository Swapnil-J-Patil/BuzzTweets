package com.example.tweet

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweet.screens.CategoryScreen
import com.example.tweet.screens.DetailScreen
import com.example.tweet.ui.theme.TweetTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetTheme {
            Scaffold(
                topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.White
                        ), title = {
                            Text(
                                text = "Buzz Tweets",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(9.dp, 7.dp)
                                    .fillMaxWidth(1f),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                style = TextStyle(
                                    Brush.linearGradient(
                                        colors = listOf(
                                           colorResource(id = R.color.color1),
                                            colorResource(id = R.color.color2),
                                        )
                                    )
                                )
                            )
                        })

                }
            ) {
                Box(modifier = Modifier.padding(it)) {
                    App()
                }
            }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun App() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination ="category"){
        composable(route="category"){
            CategoryScreen{
                navController.navigate("detail/${it}")
            }
        }
        composable(route="detail/{category}",
            arguments = listOf(
                navArgument("category"){
                    type= NavType.StringType
                }
            )
        ){
            DetailScreen()
        }
    }
}