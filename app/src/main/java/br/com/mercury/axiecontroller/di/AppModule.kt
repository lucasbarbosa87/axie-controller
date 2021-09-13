package br.com.mercury.axiecontroller.di

import br.com.mercury.axiecontroller.ui.login.LoginViewModel
import br.com.mercury.axiecontroller.ui.main.MainActivityViewModel
import br.com.mercury.axiecontroller.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainActivityViewModel(androidApplication(), get(), get()) }
    viewModel { SplashViewModel(androidApplication(), get(), get()) }
    viewModel { LoginViewModel(androidApplication(), get()) }
}