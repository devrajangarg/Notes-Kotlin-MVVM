package custome.exoplayer.com.kotlinapp.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import custome.exoplayer.com.kotlinapp.R
import custome.exoplayer.com.kotlinapp.model.Note
import custome.exoplayer.com.kotlinapp.viewModel.NotesViewModel


class AddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_add, container, false)
        view.findViewById<MaterialButton>(R.id.btn_save).setOnClickListener {

            val title: String = view.findViewById<EditText>(R.id.et_title).text.toString()
            val note: String = view.findViewById<EditText>(R.id.et_note).text.toString()

            val viewModel: NotesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)

            viewModel.createNote(title, note).observe(this, Observer<Note> {
                activity?.onBackPressed()
            })
        }
        return view
    }


}
