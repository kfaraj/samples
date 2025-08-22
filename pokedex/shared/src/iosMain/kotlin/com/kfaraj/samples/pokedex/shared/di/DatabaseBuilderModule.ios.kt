package com.kfaraj.samples.pokedex.shared.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Module
actual object DatabaseBuilderModule {

    @Single
    actual fun provideApplicationDatabaseBuilder(
        scope: Scope
    ): RoomDatabase.Builder<ApplicationDatabase> {
        return Room.databaseBuilder(
            requireNotNull(
                NSFileManager.defaultManager.URLForDirectory(
                    NSDocumentDirectory,
                    NSUserDomainMask,
                    null,
                    false,
                    null
                )?.path
            )
        )
    }

}
