package com.example.memoandroidviews.viewmodel

import androidx.lifecycle.*
import com.example.memoandroidviews.data.Memo
import com.example.memoandroidviews.repository.MemoRepository
import kotlinx.coroutines.launch

class MemoViewModel(private val repository: MemoRepository) : ViewModel() {
    val allMemos: LiveData<List<Memo>> = repository.allMemos

    fun insert(content: String) {
        viewModelScope.launch {
            repository.insert(Memo(content = content))
        }
    }

    fun delete(memo: Memo) {
        viewModelScope.launch {
            repository.delete(memo)
        }
    }

    fun update(memo: Memo) {
        viewModelScope.launch {
            repository.update(memo)
        }
    }
}