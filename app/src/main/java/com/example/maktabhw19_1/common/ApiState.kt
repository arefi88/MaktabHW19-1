package com.example.maktabhw19_1.common

sealed class ApiState<out T>{
   data class Success<out R>(val data:R):ApiState<R>()
    data class Error(val msg:String):ApiState<Nothing>()
    object Loading:ApiState<Nothing>()
}
