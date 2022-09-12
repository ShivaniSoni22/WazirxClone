package com.wazirx.exchange.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wazirx.exchange.pojo.CoinInfo

@Database(entities = [CoinInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "Crypto.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                db = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                return db as AppDatabase
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}
