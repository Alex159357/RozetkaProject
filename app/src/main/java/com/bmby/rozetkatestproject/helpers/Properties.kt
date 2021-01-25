package com.bmby.rozetkatestproject.helpers

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Properties {
    companion object{
        @SuppressLint("SimpleDateFormat")
        fun getDate(milliSeconds: Long, dateFormat: String?): String? {
            val formatter = SimpleDateFormat(dateFormat)
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }
    }
}


val Long?.toDate: String?
    get() {
        return Properties.getDate(this!!, "dd.MM.yyyy")
    }