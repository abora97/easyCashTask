package com.abora.perfectobase.di.module

import com.abora.perfectobase.data.remote.reporsitory.MainRepository
import com.abora.perfectobase.utils.AppManger
import org.koin.dsl.module

val classesModule = module {
    single { MainRepository(get()) }
    single { AppManger(get()) }
}