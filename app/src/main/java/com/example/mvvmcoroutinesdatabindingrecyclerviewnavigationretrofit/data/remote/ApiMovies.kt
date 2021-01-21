package com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiMovies {
    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): IApiMovies {
        return getInstance().create(IApiMovies::class.java)
    }
}