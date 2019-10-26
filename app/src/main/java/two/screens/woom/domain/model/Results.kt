package two.screens.woom.domain.model

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class Results (
    @SerializedName("results") val randomItems: List<RandomItem>
)