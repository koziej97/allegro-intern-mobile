package com.example.allegrointernmobile.model.githubApi

import com.example.allegrointernmobile.model.githubApi.GithubApiService.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object GithubApiLanguages {
    private val retrofitLanguages = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val retrofitService : GithubApiService by lazy {
        retrofitLanguages.create(GithubApiService::class.java) }
}