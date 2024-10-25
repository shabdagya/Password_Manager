package eu.tutorials.mywishlistapp

import android.content.Context
import androidx.room.Room
import eu.tutorials.mywishlistapp.data.PwdDatabase
import eu.tutorials.mywishlistapp.data.PwdRepository

object Graph {
    lateinit var database: PwdDatabase

    val pwdRepository by lazy{
        PwdRepository(pwdDAO = database.pwdDAO())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, PwdDatabase::class.java, "wishlist.db").build()
    }

}