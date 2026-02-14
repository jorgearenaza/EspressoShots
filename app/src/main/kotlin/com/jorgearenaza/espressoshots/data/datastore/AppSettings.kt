package com.jorgearenaza.espressoshots.data.datastore

data class AppSettings(
    val defaultDoseG: Double = 18.0,
    val defaultRatio: Double = 2.0,
    val defaultYieldG: Double = 36.0, // 18.0 * 2.0
    val defaultGrinderId: Long? = null,
    val defaultProfileId: Long? = null,
    val autofillShots: Boolean = true
)
