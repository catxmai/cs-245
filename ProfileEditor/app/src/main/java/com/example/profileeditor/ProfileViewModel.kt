package com.example.profileeditor

import android.widget.DatePicker
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ProfileViewModel:ViewModel() {
    var datePicker = MutableLiveData<DatePicker>()
    var name = MutableLiveData<String>()
    var bio = MutableLiveData<String>()
    var genderButtonId = MutableLiveData<Int>()
    var profileList = MutableLiveData<Profile>()

    init{
        profileList.value = Profile("John Smith", "Male", " ", 1, 1, 2000)
    }

    fun addProfile(prof:Profile){
        profileList.value = prof
    }

    fun getName():String{
        var res = ""
        return res+profileList.value?.name
    }

    fun getGender():String{
        var res = ""
        return res+ profileList.value?.gender
    }


    fun getBio():String{
        var res = ""
        return res+ profileList.value?.bio
    }


    fun getDOB():String{

        var text = ""
        text +="${profileList.value?.dob_date?.plus(1)}/${profileList.value?.dob_month}/${profileList.value?.dob_year}"
        return text
    }
}