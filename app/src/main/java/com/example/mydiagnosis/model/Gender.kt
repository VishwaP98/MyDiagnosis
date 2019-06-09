package com.example.mydiagnosis.model

import com.google.gson.annotations.SerializedName

enum class Gender(val string: String) {

    @SerializedName("male")
    MALE("Male"),

    @SerializedName("female")
    FEMALE("Female"),

    @SerializedName("both")
    BOTH("Both")

}