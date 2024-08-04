package com.desserttime.core.network.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RefreshTokenClient
