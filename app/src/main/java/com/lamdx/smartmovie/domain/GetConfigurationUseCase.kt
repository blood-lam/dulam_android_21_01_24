package com.lamdx.smartmovie.domain

import com.lamdx.smartmovie.model.AppConfiguration
import com.lamdx.smartmovie.repository.impl.RestApiServiceImpl
import com.lamdx.smartmovie.utils.Constant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConfigurationUseCase @Inject constructor(
    private val apiHelper: RestApiServiceImpl
) {
    operator fun invoke(): Flow<AppConfiguration> =
        apiHelper.getAppConfiguration(Constant.API_KEY)
}