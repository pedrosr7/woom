package two.screens.woom.domain.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class RandomItem (
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: Name,
    @SerializedName("login") val login: Login,
    @SerializedName("location") val location: Location,
    @SerializedName("email") val email: String,
    @SerializedName("registered") val registered: Registered,
    @SerializedName("phone") val phone: String,
    @SerializedName("picture") val picture: Picture
): Serializable