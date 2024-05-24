package com.example.prueba_softtek.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.Movie

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movie: Movie, onList:()->Unit) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Box {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.FillWidth,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                        .crossfade(2000)
                        .build(),
                    contentDescription = movie.title
                )
                IconButton(
                    onClick = {
                        onList()
                    }
                ){
                    Image(imageVector = Icons.Sharp.KeyboardArrowLeft, contentDescription = "" )
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = movie.title,
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontStyle = FontStyle.Normal
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Card(

                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors( Color(android.graphics.Color.parseColor("#2ECC71"))),
                    )
                    {
                        Row( modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp),) {
                            Icon(imageVector = Icons.Sharp.DateRange, contentDescription = "date")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = movie.releaseDate,
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontStyle = FontStyle.Normal
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors( Color(android.graphics.Color.parseColor("#D35400"))),
                    )
                    {
                        Row( modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp),) {
                            Icon(imageVector = Icons.Sharp.ThumbUp, contentDescription = "date")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = movie.voteAverage.toString(),
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontStyle = FontStyle.Normal
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = movie.overview,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Normal
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

        }

}