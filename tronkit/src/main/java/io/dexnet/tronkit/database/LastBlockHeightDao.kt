package io.dexnet.tronkit.database

import androidx.room.*
import io.dexnet.tronkit.models.LastBlockHeight

@Dao
interface LastBlockHeightDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lastBlockHeight: LastBlockHeight)

    @Query("SELECT * FROM LastBlockHeight")
    fun getLastBlockHeight(): LastBlockHeight?
}
