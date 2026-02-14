package com.jorgearenaza.espressoshots.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shots",
    foreignKeys = [
        ForeignKey(
            entity = BeanEntity::class,
            parentColumns = ["id"],
            childColumns = ["beanId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GrinderEntity::class,
            parentColumns = ["id"],
            childColumns = ["grinderId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profileId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["dateMs"]),
        Index(value = ["beanId"]),
        Index(value = ["grinderId"]),
        Index(value = ["profileId"])
    ]
)
data class ShotEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateMs: Long, // epoch millis
    val beanId: Long, // FK requerido
    val grinderId: Long? = null, // FK opcional
    val profileId: Long? = null, // FK opcional
    val doseG: Double,
    val yieldG: Double,
    val ratio: Double, // yieldG / doseG (se calcula y se guarda)
    val timeSeconds: Int? = null,
    val temperatureC: Double? = null,
    val grindAdjustment: String? = null, // "6.5", "12 clicks", "espresso", etc.
    val notes: String? = null,
    val rating: Int? = null, // 1-5
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
