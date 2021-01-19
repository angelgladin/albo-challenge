package me.angelgladin.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.angelgladin.data.entity.Beer
import me.angelgladin.master.data.BeerDao


@Database(
    entities = [Beer::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun beersDao(): BeerDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            Log.i("AppDatabase", "databasecreated1")
            return Room.databaseBuilder(context, AppDatabase::class.java, "beers-db")
                .build()
        }
    }
}
