package com.example.fabhotels.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun getTime(updatedTime: String): CharSequence {
            val dateFormat = SimpleDateFormat("HH:mm:a", Locale.getDefault())
            val date = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()
            ).parse(updatedTime)

            return dateFormat.format(date)
        }
    }
}