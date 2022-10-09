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

    private var _reposTempList : List<RepositoryInfo> = emptyList()
    private var _reposList : MutableList<RepositoryInfo?> = mutableListOf()

    private val _repos = MutableLiveData<List<RepositoryInfo?>>()
    val repos: LiveData<List<RepositoryInfo?>> = _repos

    private val _errorCode = MutableLiveData<Int>()
    val errorCode: LiveData<Int> = _errorCode

    private var repositoryPage: Int = 1

    fun increaseRepositoryPage(){
        repositoryPage++
    }

    fun getRepositories() {
        _status.value = "LOADING"
        viewModelScope.launch {
            try {
                _reposList.remove(null)
                _reposTempList = GithubApiRepositories.retrofitService.getRepositories(userName, repositoryPage)
                if (_reposTempList.isNotEmpty()) {
                    _reposList.addAll(_reposTempList)
                    _reposList.add(null)
                }
                _repos.value = _reposList
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