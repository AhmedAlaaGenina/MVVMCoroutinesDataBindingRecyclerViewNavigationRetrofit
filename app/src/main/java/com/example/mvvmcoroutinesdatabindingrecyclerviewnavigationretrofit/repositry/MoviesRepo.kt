package com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.repositry

import androidx.lifecycle.MutableLiveData
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.data.remote.ApiMovies
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.data.remote.Constant
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.model.MoviesPogoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//this package and class that get data from local or remote

class MoviesRepo {
    var moviesResponseMutableLiveData: MutableLiveData<MoviesPogoResult> = MutableLiveData()

    fun fetchMovies(): MutableLiveData<MoviesPogoResult> {
        GlobalScope.launch {
            Dispatchers.IO
            val response = ApiMovies.getApiService()
                .getMovies(Constant.API_KEY)
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    moviesResponseMutableLiveData.value = response.body()
                }

            }
        }
        return moviesResponseMutableLiveData
    }
}