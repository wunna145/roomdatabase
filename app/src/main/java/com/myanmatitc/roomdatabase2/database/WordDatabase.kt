package com.myanmatitc.roomdatabase2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myanmatitc.roomdatabase2.dao.WordDao
import com.myanmatitc.roomdatabase2.entity.Word
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Word:: class),version = 1)
abstract class WordDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object{
        private var INSTANCE: WordDatabase? = null
        fun getDatabase(
            context: Context, scope: CoroutineScope
        ): WordDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
