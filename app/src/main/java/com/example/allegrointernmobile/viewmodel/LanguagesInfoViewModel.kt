package com.example.allegrointernmobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allegrointernmobile.model.githubApi.GithubApiLanguages
import kotlinx.coroutines.launch
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class LanguagesInfoViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _languages = MutableLiveData<String>()

    lateinit var repoName : String
    lateinit var userName : String

    var listOfLanguages : String = ""

    fun getLanguages() {
        viewModelScope.launch {
            try {
                _languages.value = GithubApiLanguages.retrofitService.getLanguages(userName, repoName)

                // PARSE STRING TO JSON
                val json = _languages.value
                val gson = Gson()
                val tutorialMap: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)

                // GETTING DATA FROM JSON AND PUTTING IN ORDER IN STRING
                val allKeys = tutorialMap.keys
                for (name in allKeys){
                    var value = tutorialMap[name]
                    value = "%.0f".format(value)
                    listOfLanguages += "\n $name : $value bajtów"
                }

                _status.value = "SUCCESS"

            } catch (e: Exception) {
                _status.value = "ERROR"
                Log.e("Get Languages", "$e")
            }
        }
    }
}