package com.ubaya.studentproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.studentproject.model.Student

class ListViewModel: ViewModel() {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val loadingDL = MutableLiveData<Boolean>()
    val errorLD = MutableLiveData<Boolean>()

    fun refresh() {
        loadingDL.value = true // progress bar start muncul
        errorLD.value = false // tidak ada error

        studentsLD.value = arrayListOf(
            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100"
                    + ".jpg/cc0000/ffffff"),
            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
                    ".jpg/5fa2dd/ffffff"),
            Student("11204","Dinny","1994/10/07","6827808747",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        )

        loadingDL.value = false // progress bar stop
        errorLD.value = false
    }
}