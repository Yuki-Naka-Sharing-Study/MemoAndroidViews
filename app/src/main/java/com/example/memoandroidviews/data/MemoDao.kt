package com.example.memoandroidviews.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemoDao {
    @Query("SELECT * FROM memos ORDER BY timestamp DESC")
    fun getAllMemos(): LiveData<List<Memo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memo: Memo)

    @Delete
    suspend fun delete(memo: Memo)
}