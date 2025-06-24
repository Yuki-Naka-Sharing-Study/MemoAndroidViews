package com.example.memoandroidviews.repository

import android.util.Log
import com.example.memoandroidviews.data.Memo
import com.example.memoandroidviews.data.MemoDao

class MemoRepository(private val memoDao: MemoDao) {
    val allMemos = memoDao.getAllMemos()

    suspend fun insert(memo: Memo) {
        memoDao.insert(memo)
        Log.d("MemoViewModel", "Inserting memo: $memo")
    }

    suspend fun delete(memo: Memo) {
        memoDao.delete(memo)
    }
}