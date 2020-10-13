package com.myanmatitc.roomdatabase2.repository

import androidx.lifecycle.LiveData
import com.myanmatitc.roomdatabase2.dao.WordDao
import com.myanmatitc.roomdatabase2.entity.Word

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

    fun searchWord(word: String): LiveData<List<Word>>{
        return wordDao.getSearchWords(word)
    }

    suspend fun deleteWord(word:String){
        return wordDao.delete(word)
    }

    suspend fun updateWord(word:String, update_word:String){
        return wordDao.update(word,update_word)
    }

}