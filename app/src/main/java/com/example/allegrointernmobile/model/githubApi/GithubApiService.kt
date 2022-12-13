package com.example.allegrointernmobile.model.githubApi

import com.example.allegrointernmobile.model.dao.RepositoryInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("users/{username}/repos")
    suspend fun getRepositories(
        @Path("username") username: String,
        @Query("page") page: Int
    ): List<RepositoryInfo>

    @GET("repos/{username}/{repoName}/languages")
    suspend fun getLanguages(
        @Path("username") username: String,
        @Path("repoName") repoName: String
    ): String
}