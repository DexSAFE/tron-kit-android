package io.dexnet.tronkit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.dexnet.tronkit.models.Balance
import io.dexnet.tronkit.models.ChainParameter
import io.dexnet.tronkit.models.InternalTransaction
import io.dexnet.tronkit.models.LastBlockHeight
import io.dexnet.tronkit.models.Transaction
import io.dexnet.tronkit.models.TransactionSyncState
import io.dexnet.tronkit.models.TransactionTag
import io.dexnet.tronkit.models.Trc20EventRecord

@Database(
    entities = [
        LastBlockHeight::class,
        Balance::class,
        TransactionSyncState::class,
        Transaction::class,
        InternalTransaction::class,
        Trc20EventRecord::class,
        TransactionTag::class,
        ChainParameter::class,
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(RoomTypeConverters::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun lastBlockHeightDao(): LastBlockHeightDao
    abstract fun balanceDao(): BalanceDao
    abstract fun transactionDao(): TransactionDao
    abstract fun tagsDao(): TransactionTagDao
    abstract fun chainParameterDao(): ChainParameterDao

    companion object {
        fun getInstance(context: Context, databaseName: String): MainDatabase {
            return Room.databaseBuilder(context, MainDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}
