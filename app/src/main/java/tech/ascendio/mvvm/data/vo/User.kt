package tech.ascendio.mvvm.data.vo

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["name"])
data class User(
        @field:SerializedName("name")
        val name: String
)