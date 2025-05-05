package com.app.testpexel.di

import com.app.testpexel.presentation.photo.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<PhotoViewModel>{PhotoViewModel(get())}
}