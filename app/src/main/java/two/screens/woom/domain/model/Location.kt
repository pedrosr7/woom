package two.screens.woom.domain.model

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("street") val street: Street,
    @SerializedName("city") val city: String,
    @SerializedName("state") val title: String
)