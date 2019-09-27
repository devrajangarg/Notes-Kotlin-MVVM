package custome.exoplayer.com.kotlinapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Note(
        @PrimaryKey
        @SerializedName("_id") val _id: String,
        @SerializedName("title") val title: String,
        @SerializedName("note") val note: String,
        @SerializedName("__v") val __v: Int
)