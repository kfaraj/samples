package com.kfaraj.samples.koin.multimodule

import com.kfaraj.samples.koin.multimodule.feature.FeatureModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [FeatureModule::class])
@ComponentScan
object AppModule
