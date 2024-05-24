package com.example.prueba_softtek.ui.listRoom

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.potencie21compose.ui.theme.PrimaryLight
import com.example.prueba_softtek.component.DialogLoading
import com.example.prueba_softtek.viewModel.ListRoomViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListRoomScreen(viewModel: ListRoomViewModel = koinViewModel(), onDetail:(Movie)->Unit) {
    val state = viewModel.state
    val context = LocalContext.current
    val ptrState= rememberPullRefreshState(state.isRefreshing, {viewModel.getMovies()})

    LaunchedEffect( true){
            viewModel.getMovies()
    }

    LaunchedEffect( Unit){
        viewModel.uiState.collect{
            when(it){
                is ListRoomUiState.Error -> {
                    Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                    viewModel.state = viewModel.state.copy(isLoading = false)
                }
                is ListRoomUiState.Loading -> {
                    viewModel.state = viewModel.state.copy(isLoading = true)
                }
                is ListRoomUiState.Nothing -> {}
                is ListRoomUiState.Success -> {
                    viewModel.state = viewModel.state.copy(movies = it.list,isLoading = false)
                }
            }
        }
    }



    Scaffold(
        topBar ={
            TopAppBar(
                navigationIcon = {

                },
                title = {
                    Text(text = "Peliculas Room")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = PrimaryLight
                ),
                actions = {
                }
            )
        }
    ) {
        Surface(modifier = Modifier
            .padding(top = it.calculateTopPadding())
            .fillMaxSize()
        ) {
            if (state.isLoading==true){
                DialogLoading(true)
            }else{
                Box(Modifier.pullRefresh(ptrState)) {
                    LazyColumn(
                        contentPadding = PaddingValues(8.dp),
                    ) {
                        state.movies?.let { movies ->
                            items(movies){movie->
                                ItemMovie(movie = movie){
                                    onDetail(it)
                                }
                            }
                        }
                    }
                    PullRefreshIndicator(state.isRefreshing, ptrState, Modifier.align(Alignment.TopCenter) )
                }
            }
        }
    }
}

@Composable
fun ItemMovie(
    movie: Movie,
    onDetail:(Movie)->Unit
) {
    Card(
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onDetail(movie)
            }
    ) {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.FillWidth,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                        .crossfade(2000)
                        .build(),
                    contentDescription = movie.title
                )
            Card(
                colors = CardDefaults.cardColors( Color(android.graphics.Color.parseColor("#f1f1f1"))),
            )
            {
                Text(modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    text = movie.title,
                    fontStyle = FontStyle.Italic,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}