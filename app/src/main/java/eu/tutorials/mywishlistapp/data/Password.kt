package eu.tutorials.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="wish-table")
data class Password(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name="wish-title")
    val title: String="",
    @ColumnInfo(name="wish-desc")
    val description:String="",
    @ColumnInfo(name="wish-web")
    val website:String=""
)

