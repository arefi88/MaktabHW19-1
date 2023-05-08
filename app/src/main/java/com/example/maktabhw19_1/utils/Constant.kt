package com.example.maktabhw19_1.utils

import com.example.maktabhw19_1.common.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


const val API_KEY="a447989f2b34e1193b1194c6265c3d3f"
const val BASE_URL="https://api.themoviedb.org/3/"
const val TAG="MainActivity"

fun<T> toResultFlow(call:suspend()->Response<T>):Flow<ApiState<T>> = flow {
    emit(ApiState.Loading)
    val response=call()
    try {
        if (response.isSuccessful){
            response.body()?.let {data->
                emit(ApiState.Success(data))
            }
        }else{
            response.errorBody()?.let { error->
                error.close()
                emit(ApiState.Error(error.toString()))
            }
        }
    }catch (e:Exception){
        emit(ApiState.Error(e.message.toString()))
    }

}

