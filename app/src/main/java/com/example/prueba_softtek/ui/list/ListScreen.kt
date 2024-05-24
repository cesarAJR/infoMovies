package com.example.prueba_softtek.ui.list

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.material.icons.sharp.List
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.Movie
import com.example.potencie21compose.ui.theme.PrimaryLight
import com.example.prueba_softtek.component.DialogLoading
import com.example.prueba_softtek.viewModel.ListViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListScreen(viewModel: ListViewModel = koinViewModel(), onDetail:(Movie)->Unit, onLogin:()->Unit,onListRoom:()->Unit) {
    val state = viewModel.state
    var callService by rememberSaveable {
        mutableStateOf(true)
    }
    val context = LocalContext.current
    val ptrState= rememberPullRefreshState(state.isRefreshing, {
        viewModel.cleanList()
        viewModel.getMovies()
    })

    val moviePagingItems: LazyPagingItems<Movie> = viewModel.uiState.collectAsLazyPagingItems()

    LaunchedEffect( true){
        if (callService){
            viewModel.getMovies()
            callService = false
        }
    }
    Scaffold(
        topBar ={
            TopAppBar(
                navigationIcon = {

                },
                    title = {
                        Text(text = "Peliculas")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = PrimaryLight
                    ),
                    actions = {
                    Row{
                        Icon(
                            imageVector = Icons.Sharp.List,
                            contentDescription = "list",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable {
                                    onListRoom()
                                }
                        )
                        Icon(
                            imageVector = Icons.Sharp.ExitToApp,
                            contentDescription = "Information",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable {
                                    onLogin()
                                    viewModel.resetLogin()
                                }
                        )
                    }

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
                        items(moviePagingItems.itemCount){index->
                            ItemMovie(movie = moviePagingItems.get(index)!!){
                                onDetail(it)
                            }
                        }
                        moviePagingItems.apply {
                            when{
                               loadState.refresh is LoadState.Loading->{
                                 item{
                                      PageLoader(modifier = Modifier.fillParentMaxSize())
                                 }
                               }
                                loadState.refresh is LoadState.Error -> {

                                }
                                loadState.append is LoadState.Loading -> {
                                    item { LoadingNextPageItem(modifier = Modifier) }
                                }
                                loadState.append is LoadState.Error -> {

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

@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "cargando",
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}