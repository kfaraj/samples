package com.kfaraj.samples.koin.multimodule.feature

import com.kfaraj.samples.koin.multimodule.data.DataModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DataModule::class])
@ComponentScan
public object FeatureModule
