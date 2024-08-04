package com.desserttime.core.network.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RefreshTokenRetrofit
