package eu.tutorials.mywishlistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [Password::class],
    version = 1,
    exportSchema = false
)
abstract class PwdDatabase : RoomDatabase() {
    abstract fun pwdDAO(): PwdDAO
}