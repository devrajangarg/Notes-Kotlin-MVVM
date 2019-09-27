package custome.exoplayer.com.kotlinapp.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.google.gson.JsonObject
import custome.exoplayer.com.kotlinapp.model.DeleteNotePojo
import custome.exoplayer.com.kotlinapp.model.Note
import custome.exoplayer.com.kotlinapp.repository.NotesRepository
import javax.inject.Inject

class NotesViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    val notesRepository = NotesRepository()

    fun getNotes(): LiveData<ArrayList<Note>> {
        return notesRepository.getAllNotes()
    }

    fun getNote(id: String): LiveData<Note> {
        return notesRepository.getNote(id)
    }

    fun createNote(title: String, note: String): LiveData<Note> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("title", title)
        jsonObject.addProperty("note", note)
        return notesRepository.createNote(jsonObject)
    }

    fun updateNote(id: String, title: String, note: String): LiveData<Note> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("title", title)
        jsonObject.addProperty("note", note)
        return notesRepository.updateNote(id, jsonObject)
    }

    fun deleteNote(id: String): LiveData<DeleteNotePojo> {
        return notesRepository.deleteNote(id)
    }
}