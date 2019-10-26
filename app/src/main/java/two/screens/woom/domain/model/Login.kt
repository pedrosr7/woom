package two.screens.woom.domain.model

import com.google.gson.annotations.SerializedName

data class Login (
    @SerializedName("username") val username: String
)