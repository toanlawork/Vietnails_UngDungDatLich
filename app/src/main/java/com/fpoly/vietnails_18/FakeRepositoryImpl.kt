package com.fpoly.vietnails_18

import com.fpoly.BaseResponse
import com.fpoly.vietnails_18.data.remote.AppAPI

class FakeRepositoryImpl(private val appAPI: AppAPI) : FakeRepository {
    override suspend fun getUser(): BaseResponse {
        return appAPI.getUser()
    }

}