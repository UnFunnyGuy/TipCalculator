package com.unfunny.tip.di

import com.unfunny.tip.data.TipRepositoryImpl
import com.unfunny.tip.domain.TipRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsTipRepository(
        tipRepository: TipRepositoryImpl
    ): TipRepository
}