package com.kfaraj.samples.koin.multimodule.data

import org.koin.core.annotation.Single

@Single
internal class DefaultMainRepository : MainRepository {
    override val message: String = "Hello Koin!"
}
