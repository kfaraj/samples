package com.kfaraj.samples.koin.multimodule.feature

import com.kfaraj.samples.koin.multimodule.data.MainRepository
import org.koin.core.annotation.Factory

@Factory
public class MainViewModel internal constructor(
    repository: MainRepository
) {
    public val uiState: String = repository.message
}
