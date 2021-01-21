package com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.model.MoviesPogoResult
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.repositry.MoviesRepo

class MoviesView : ViewModel() {

    var moviesRepo:MoviesRepo = MoviesRepo()

    fun fetchMovies():MutableLiveData<MoviesPogoResult>{
        return moviesRepo.fetchMovies()
    }
}