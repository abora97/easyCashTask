package com.abora.perfectobase.di.module


import com.abora.perfectobase.ui.main.MainViewModel
import com.abora.perfectobase.ui.splash.SplashViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel


val viewModelModule = module {
    viewModel { SplashViewModel(get(),get()) }
    viewModel { MainViewModel(get(),get()) }

}
