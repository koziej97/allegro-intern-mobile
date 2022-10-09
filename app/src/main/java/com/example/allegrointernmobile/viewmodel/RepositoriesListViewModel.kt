package com.example.allegrointernmobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allegrointernmobile.adapter.ReposItemClickListener
import com.example.allegrointernmobile.model.GithubApiRepositories
import com.example.allegrointernmobile.model.RepositoryInfo
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RepositoriesListViewModel : ViewModel(), ReposItemClickListener {

    lateinit var userName : String

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _repos = MutableLiveData<List<RepositoryInfo>>()
    val repos: LiveData<List<RepositoryInfo>> = _repos

    private val _errorCode = MutableLiveData<Int>()
    val errorCode: LiveData<Int> = _errorCode

    fun getRepositories() {
        viewModelScope.launch {
            try {
                _repos.value = GithubApiRepositories.retrofitService.getRepositories(userName, 1)
                _status.value = "SUCCESS"
                Log.d("Get Repository", "Success!")
            } catch (e: HttpException) {
                _errorCode.value = e.code()
                _status.value = "ERROR"
                Log.e("Get Repository", "$e")
            }
        }
    }
}