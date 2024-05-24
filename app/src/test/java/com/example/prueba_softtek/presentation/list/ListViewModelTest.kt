package com.example.prueba_softtek.presentation.list

import com.example.prueba_softtek.data.repository.MovieRepository
import com.example.domain.model.Movie
import com.example.prueba_softtek.viewModel.ListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ListViewModelTest{
    @MockK
    lateinit var repositoryImp:MovieRepository

    @RelaxedMockK
    private lateinit var movies: List<Movie>

    private lateinit var viewmodel : ListViewModel

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewmodel =  ListViewModel(repositoryImp)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `get movies return data`()= runTest {
        val flow = flow {
            emit(Result.Successfull(movies))
        }

       coEvery { repositoryImp.getMovies()} returns flow
        viewmodel.getMovies()
        assert(viewmodel.state.movies!=null)
    }

    @Test
    fun `get movies return 20 data`()= runTest {
        val flow = flow {
           emit(Result.Successfull(movies))
        }
        coEvery { repositoryImp.getMovies()} returns flow
        viewmodel.getMovies()
        assert(viewmodel.state.movies!!.size==20)
    }

}