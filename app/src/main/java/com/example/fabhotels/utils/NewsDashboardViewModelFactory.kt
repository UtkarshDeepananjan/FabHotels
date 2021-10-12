package com.example.fabhotels.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsDashboardViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }
    /* override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         if (modelClass.isAssignableFrom(NewsDashboardViewModel::class.java)) {
             return NewsDashboardViewModel(
                 application = Application()

             ) as T
         }
         throw IllegalArgumentException("Unknown ViewModel class")
     }*/
}