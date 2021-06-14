package com.fpoly.vietnails_18

import com.fpoly.BaseResponse

interface FakeRepository {
    suspend fun getUser(): BaseResponse
}