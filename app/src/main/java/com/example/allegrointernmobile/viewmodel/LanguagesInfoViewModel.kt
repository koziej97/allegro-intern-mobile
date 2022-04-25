package com.example.allegrointernmobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allegrointernmobile.model.GithubApiLanguages
import kotlinx.coroutines.launch
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.login.LoginException

enum class GithubApiService { LOADING, ERROR, DONE }

class LanguagesInfoViewModel : ViewModel() {

    private val _status = MutableLiveData<GithubApiService>()
    val status: LiveData<GithubApiService> = _status

    private val _languages = MutableLiveData<String?>()
    val languages: MutableLiveData<String?> = _languages

    lateinit var repoName : String
    lateinit var userName : String
    lateinit var response : Any

    var listOfLanguages : String = ""

    fun getLanguages() {
        viewModelScope.launch {
            _status.value = GithubApiService.LOADING
            response = try {
                _languages.value = GithubApiLanguages.retrofitService.getLanguages(userName, repoName)

                // PARSE STRING TO JSON
                val json = _languages.value
                val gson = Gson()
                val tutorialMap: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)

                listOfLanguages = tutorialMap.toString()
                _status.value = GithubApiService.DONE
/*
                val allKeys = tutorialMap.keys
                for (i in allKeys){
                    val name = i
                    val value = tutorialMap[i]
                    println(name)
                    println(value)
                }
 */

            } catch (e: Exception) {
                _status.value = GithubApiService.ERROR
                Log.e("Get Languages", "$e")
            }
        }
    }
}