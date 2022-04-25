package com.example.allegrointernmobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import com.example.allegrointernmobile.adapter.ReposItemClickListener
import com.example.allegrointernmobile.model.GithubApi
import com.example.allegrointernmobile.model.GithubApiService
import com.example.allegrointernmobile.model.RepositoryInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SharedViewModel : ViewModel(), ReposItemClickListener {

    lateinit var username : String

    private val _repos = MutableLiveData<List<RepositoryInfo>>()
    val repos: LiveData<List<RepositoryInfo>> = _repos

    fun getRepositories() {
        viewModelScope.launch {
            try {
                _repos.value = GithubApi.retrofitService.getRepositories(username)
                Log.d("Get Repository", "Success!")
            } catch (e: Exception) {
                Log.e("Get Repository", "$e", )
            }
        }
    }
}