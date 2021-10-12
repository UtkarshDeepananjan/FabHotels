package com.example.fabhotels.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fabhotels.data.model.LoginUser
import com.example.fabhotels.repository.Repository

class LoginViewModel : ViewModel() {
    private lateinit var mLoginData: LiveData<LoginUser?>

    fun getLoginData(): LiveData<LoginUser?> {
        mLoginData = Repository().getLoginData()
        return mLoginData
    }

}