package com.example.fridge.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fridge.modelF.ProductsF

@Database(entities = [ProductsF::class], version = 3, exportSchema = false)
abstract class ProductsDatabase: RoomDatabase() {

    abstract fun productsDao(): ProductsDao

    companion object{
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        val migration_1_2: Migration = object: Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE products_table ADD COLUMN dateP TEXT DEFAULT ''")
            }
        }

        val migration_2_3: Migration = object: Migration(2, 3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE products_table ADD COLUMN countD INTEGER DEFAULT 0")
            }
        }

        fun getDatabase(context: Context): ProductsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products_database"
                ).addMigrations(migration_1_2, migration_2_3).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}