package me.angelgladin.master.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.angelgladin.data.entity.Beer

@Dao
interface BeerDao {
    @Query("SELECT * FROM beer")
    fun getBeers(): List<Beer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<Beer>)
}