package eu.tutorials.mywishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PwdDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAPwd(passwordEntity: Password)

    // Loads all wishes from the wish table
    @Query("Select * from `wish-table`")
    abstract fun getAllPwds(): Flow<List<Password>>

    @Update
    abstract suspend fun updateAPwd(passwordEntity: Password)

    @Delete
    abstract suspend fun deleteAPwd(passwordEntity: Password)

    @Query("Select * from `wish-table` where id=:id")
    abstract fun getAPwdById(id:Long): Flow<Password>


}