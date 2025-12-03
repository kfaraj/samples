package com.kfaraj.samples.pokedex.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

/**
 * Annotates the default [CoroutineDispatcher].
 */
@Named
annotation class DefaultDispatcher

/**
 * Annotates the I/O [CoroutineDispatcher].
 */
@Named
annotation class IoDispatcher

/**
 * Annotates the application [CoroutineScope].
 */
@Named
annotation class ApplicationScope

/**
 * Provides bindings for coroutines.
 */
@Module
@Configuration
object CoroutinesModule {

    /**
     * Provides the default [CoroutineDispatcher].
     */
    @DefaultDispatcher
    @Factory
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    /**
     * Provides the I/O [CoroutineDispatcher].
     */
    @IoDispatcher
    @Factory
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    /**
     * Provides the application [CoroutineScope].
     */
    @ApplicationScope
    @Single
    fun provideApplicationScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + defaultDispatcher)
    }

}
