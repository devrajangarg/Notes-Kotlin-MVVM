package custome.exoplayer.com.kotlinapp.repository

import com.google.gson.JsonObject
import custome.exoplayer.com.kotlinapp.model.DeleteNotePojo
import custome.exoplayer.com.kotlinapp.model.Note
import retrofit2.Call
import retrofit2.http.*

interface Webservice {

    @GET("notes")
    fun getNotes(): Call<ArrayList<Note>>

    @POST("notes")
    fun createNote(@Body note: JsonObject): Call<Note>

    @PUT("notes/{id}")
    fun updateNote(@Path("id") id: String, @Body note: JsonObject): Call<Note>

    @GET("notes/{id}")
    fun getNote(@Path("id") id: String): Call<Note>

    @DELETE("notes/{id}")
    fun deleteNote(@Path("id") id: String): Call<DeleteNotePojo>

}