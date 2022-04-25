package com.example.allegrointernmobile.model

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
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

// LANGUAGES
private val retrofitLanguages = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface GithubApiService {
    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String): List<RepositoryInfo>

    @GET("repos/{username}/{repoName}/languages")
    suspend fun getLanguages(
        @Path("username") username: String,
        @Path("repoName") repoName: String
    ): String
}

object GithubApiRepositories {
    val retrofitService : GithubApiService by lazy {
        retrofitRepositories.create(GithubApiService::class.java) }
}

object GithubApiLanguages {
    val retrofitService : GithubApiService by lazy {
        retrofitLanguages.create(GithubApiService::class.java) }
}