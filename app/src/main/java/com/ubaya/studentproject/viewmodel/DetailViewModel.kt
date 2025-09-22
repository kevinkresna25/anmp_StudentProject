package com.ubaya.studentproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.studentproject.model.Student

class DetailViewModel(app: Application): AndroidViewModel(app) {
    val studentLD = MutableLiveData<Student>()
    val TAG = "volleytag"
    private var queue: RequestQueue? = null



    fun fetch(id:String) {

//        val data = arrayListOf(
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100"
//                    + ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
//                    ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747",
//                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//        )

        // volley ke API
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                // Sukses
                val sType = object: TypeToken<List<Student>>() {}.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                val arrStudent = result as ArrayList<Student>

                studentLD.value = arrStudent.find { it.id == id } as Student
            },
            {
                // Failed
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}