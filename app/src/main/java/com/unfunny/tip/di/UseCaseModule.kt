package com.unfunny.tip.di

import com.unfunny.tip.domain.TipRepository
import com.unfunny.tip.domain.use_cases.GetPercentage
import com.unfunny.tip.domain.use_cases.GetTotal
import com.unfunny.tip.domain.use_cases.SavePercentage
import com.unfunny.tip.domain.use_cases.TipUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesTipUseCases(repository: TipRepository): TipUseCases {
        return TipUseCases(
            getPercentage = GetPercentage(repository),
            savePercentage = SavePercentage(repository),
            getTotal = GetTotal(repository)
        )
    }
}
