package custome.exoplayer.com.kotlinapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import custome.exoplayer.com.kotlinapp.model.DeleteNotePojo
import custome.exoplayer.com.kotlinapp.model.Note
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NotesRepository {
    private val webservice: Webservice = getClient()

    private fun getClient(): Webservice {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://notesclone.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(Webservice::class.java)
    }

    fun getAllNotes(): LiveData<ArrayList<Note>> {
        val notes = MutableLiveData<ArrayList<Note>>()
        webservice.getNotes().enqueue(object : Callback<ArrayList<Note>> {
            override fun onResponse(call: Call<ArrayList<Note>>, response: Response<ArrayList<Note>>) {
                notes.value = response.body()
            }
            override fun onFailure(call: Call<ArrayList<Note>>, t: Throwable?) {

            }
        })
        return notes
    }

    fun createNote(jsonObject: JsonObject): LiveData<Note> {
        val note = MutableLiveData<Note>()
        webservice.createNote(jsonObject).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                note.value = response.body()
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {

            }
        })
        return note
    }

    fun updateNote(id: String, jsonObject: JsonObject): LiveData<Note> {
        val note = MutableLiveData<Note>()
        webservice.updateNote(id, jsonObject).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                note.value = response.body()
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {

            }
        })
        return note
    }

    fun getNote(id: String): LiveData<Note> {
        val note = MutableLiveData<Note>()
        webservice.getNote(id).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                note.value = response.body()
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {

            }
        })
        return note
    }

    fun deleteNote(id: String): LiveData<DeleteNotePojo> {
        val note = MutableLiveData<DeleteNotePojo>()
        webservice.deleteNote(id).enqueue(object : Callback<DeleteNotePojo> {
            override fun onResponse(call: Call<DeleteNotePojo>, response: Response<DeleteNotePojo>) {
                note.value = response.body()
            }

            override fun onFailure(call: Call<DeleteNotePojo>, t: Throwable) {

            }
        })
        return note
    }


}
