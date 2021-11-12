package com.example.fridge.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fridge.modelF.ReceptedF

@Database(entities = [ReceptedF::class], version = 2, exportSchema = false)
abstract class receptedDatabase: RoomDatabase() {

    abstract fun receptedDao(): ReceptedDao

    companion object{
        @Volatile
        private var INSTANCE: receptedDatabase? = null

        val migration_1_2: Migration = object: Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE recepted_table ADD COLUMN ColorR TEXT DEFAULT ''")
            }
        }

        fun getDatabase(context: Context): receptedDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    receptedDatabase::class.java,
                    "recepted_database"
                ).addMigrations(migration_1_2).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}