package com.example.mydiagnosis.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context

class UserViewModel : ViewModel() {

    private var userName : String = ""

    private var userAge : Int = 0

    private var userGender : String = ""

    fun saveUserInfo(context: Context, name : String, age: Int, gender : String) {

        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        val sharedPreferencesEditor = sharedPreferences.edit()

        sharedPreferencesEditor.putString("userName", name).apply()
        sharedPreferencesEditor.putInt("userAge", age).apply()
        sharedPreferencesEditor.putString("userGender", gender).apply()
    }

    fun getUserName(context: Context) : String? {
        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        return sharedPreferences.getString("userName", "")
    }

    fun getUserAge(context: Context) : Int? {

        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        return sharedPreferences.getInt("userAge", 0)
    }

    fun getUserGender(context: Context) : String? {

        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        return sharedPreferences.getString("userGender", "")
    }

}