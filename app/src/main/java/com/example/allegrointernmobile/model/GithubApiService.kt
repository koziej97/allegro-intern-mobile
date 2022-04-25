package com.example.allegrointernmobile.model

import androidx.fragment.app.activityViewModels
import com.example.allegrointernmobile.viewmodel.SharedViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.github.com/"

// REPOSITORIES
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitRepositories = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GithubApiService {
    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String): List<RepositoryInfo>
}

object GithubApi {
    val retrofitService : GithubApiService by lazy {
        retrofitRepositories.create(GithubApiService::class.java) }
}