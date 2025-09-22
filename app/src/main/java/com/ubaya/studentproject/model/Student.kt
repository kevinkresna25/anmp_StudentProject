package com.ubaya.studentproject.model

import com.google.gson.annotations.SerializedName

data class Student (
    // nama yang tidak sama dengan nama yang ada di API
    // harus menggunakan @SerializedName agar dikonversi oleh gson
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var bod:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)