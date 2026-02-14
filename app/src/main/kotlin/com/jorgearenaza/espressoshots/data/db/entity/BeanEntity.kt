package com.jorgearenaza.espressoshots.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beans")
data class BeanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val roaster: String,
    val name: String,
    val roastDateMs: Long, // epoch millis
    val purchaseDateMs: Long?, // epoch millis, nullable
    val notes: String? = null,
    val active: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
