package custome.exoplayer.com.kotlinapp.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import custome.exoplayer.com.kotlinapp.R
import custome.exoplayer.com.kotlinapp.model.DeleteNotePojo
import custome.exoplayer.com.kotlinapp.model.Note
import custome.exoplayer.com.kotlinapp.viewModel.NotesViewModel

class UpdateFragment : Fragment() {

    lateinit var viewModel: NotesViewModel
    lateinit var note: LiveData<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        note = viewModel.getNote(arguments?.get("id").toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_update_note, container, false)
//        val viewModel: NotesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)

        val etTitle: EditText = view.findViewById(R.id.et_title)
        val etNote: EditText = view.findViewById(R.id.et_note)

        note.observe(this, Observer<Note> { note ->
            etTitle.setText(note?.title)
            etNote.setText(note?.note)
        })

        view.findViewById<View>(R.id.btn_save).setOnClickListener {

            val title: String = etTitle.text.toString()
            val note: String = etNote.text.toString()

            viewModel.updateNote(arguments?.get("id").toString(), title, note)
                    .observe(this, Observer<Note> { data ->
                        activity?.onBackPressed()
                    })
        }

        view.findViewById<View>(R.id.btn_delete).setOnClickListener {
            viewModel.deleteNote(arguments?.get("id").toString())
                    .observe(this, Observer<DeleteNotePojo> { data ->
                        Toast.makeText(context, data?.message, Toast.LENGTH_SHORT).show()
                        activity?.onBackPressed()
                    })
        }

        return view
    }

}
