package com.dathanasleri.newsapp.network

import com.dathanasleri.newsapp.BuildConfig
import com.dathanasleri.newsapp.models.ArticlesList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("top-headlines?country=us&apiKey=${BuildConfig.API_KEY}")
    suspend fun getDataFromAPI(@Query("q") query: String): ArticlesList
}