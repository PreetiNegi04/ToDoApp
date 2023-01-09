package com.preeti.inventory.data

import android.os.SystemClock
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.System.currentTimeMillis

@Entity(tableName="tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String,
    @ColumnInfo (name = "is_complete")val isComplete:Boolean= false,
    val rank:Int=0,
    @ColumnInfo(name = "created_at") val createdAt:Long= System.currentTimeMillis()
)