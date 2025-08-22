package com.kfaraj.samples.pokedex.shared.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Module
actual object DatabaseBuilderModule {

    @Single
    actual fun provideApplicationDatabaseBuilder(
        scope: Scope
    ): RoomDatabase.Builder<ApplicationDatabase> {
        val applicationContext: Context = scope.get()
        return Room.databaseBuilder(
            applicationContext,
            applicationContext.getDatabasePath("pokedex.db").absolutePath
        )
    }

}
