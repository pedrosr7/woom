package two.screens.woom.domain.model

import com.google.gson.annotations.SerializedName

data class Registered (
    @SerializedName("date") val number: String,
    @SerializedName("age") val age: String
)