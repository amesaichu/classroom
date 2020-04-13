package com.amel.classroom

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var nama: String? = "",
    var nim: String? = "",
    var hakAkses: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "hakAkses" to hakAkses,
            "nama" to nama,
            "nim" to nim
        )
    }
}