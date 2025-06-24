package com.example.memoandroidviews.di

import androidx.room.Room
import com.example.memoandroidviews.data.MemoDatabase
import com.example.memoandroidviews.repository.MemoRepository
import com.example.memoandroidviews.viewmodel.MemoViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            MemoDatabase::class.java,
            "memo_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<MemoDatabase>().memoDao() }

    single { MemoRepository(get()) }

    viewModel { MemoViewModel(get()) }
}