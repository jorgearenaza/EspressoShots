package com.jorgearenaza.espressoshots.di

import android.content.Context
import com.jorgearenaza.espressoshots.data.db.AppDatabase
import com.jorgearenaza.espressoshots.data.datastore.AppSettingsManager
import com.jorgearenaza.espressoshots.data.repository.BeanRepository
import com.jorgearenaza.espressoshots.data.repository.GrinderRepository
import com.jorgearenaza.espressoshots.data.repository.ProfileRepository
import com.jorgearenaza.espressoshots.data.repository.ShotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideAppSettingsManager(@ApplicationContext context: Context): AppSettingsManager {
        return AppSettingsManager(context)
    }

    @Singleton
    @Provides
    fun provideBeanRepository(database: AppDatabase): BeanRepository {
        return BeanRepository(database.beanDao())
    }

    @Singleton
    @Provides
    fun provideGrinderRepository(database: AppDatabase): GrinderRepository {
        return GrinderRepository(database.grinderDao())
    }

    @Singleton
    @Provides
    fun provideProfileRepository(database: AppDatabase): ProfileRepository {
        return ProfileRepository(database.profileDao())
    }

    @Singleton
    @Provides
    fun provideShotRepository(database: AppDatabase): ShotRepository {
        return ShotRepository(database.shotDao())
    }
}
