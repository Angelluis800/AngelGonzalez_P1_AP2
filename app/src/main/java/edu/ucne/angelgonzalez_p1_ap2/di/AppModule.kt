package edu.ucne.angelgonzalez_p1_ap2.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.angelgonzalez_p1_ap2.data.local.database.AngelGonzalez_P1_AP2Db
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context) : AngelGonzalez_P1_AP2Db =
        Room.databaseBuilder(
            appContext,
            AngelGonzalez_P1_AP2Db::class.java,
            "ParcialDb"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideSistemaDao(parcialDb: AngelGonzalez_P1_AP2Db) = parcialDb.sistemaDao()
}