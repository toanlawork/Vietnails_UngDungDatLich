package com.fpoly.vietnails_18.data.remote

import com.fpoly.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface AppAPI {
    @GET("api/users?page=2")
    suspend fun getUser(): BaseResponse
}