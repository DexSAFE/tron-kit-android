package io.dexnet.tronkit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.dexnet.tronkit.models.TransactionTag

@Dao
interface TransactionTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tags: List<TransactionTag>)

}
