package com.example.newtestapplication.data.di

import com.example.newtestapplication.data.NewsRepo
import com.example.newtestapplication.data.NewsRepoImpl
import org.koin.dsl.module


/**
 * Injecting repositories
 */
val repositorySourceModule = module {

    single<NewsRepo> { NewsRepoImpl(get()) }
}