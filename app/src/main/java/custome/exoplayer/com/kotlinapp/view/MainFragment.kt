package custome.exoplayer.com.kotlinapp.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import custome.exoplayer.com.kotlinapp.R
import custome.exoplayer.com.kotlinapp.adapter.NotesAdapter
import custome.exoplayer.com.kotlinapp.model.Note
import custome.exoplayer.com.kotlinapp.viewModel.NotesViewModel


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_notes)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val notesAdapter = NotesAdapter(this)

        recyclerView.adapter = notesAdapter

        val viewModel: NotesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        viewModel.getNotes().observe(this, Observer<ArrayList<Note>> { data ->
            notesAdapter.setNotes(data as ArrayList<Note>)
        })

        view.findViewById<View>(R.id.fab).setOnClickListener {
            fragmentManager!!.beginTransaction()
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, 0, 0, R.anim.design_bottom_sheet_slide_out)
                    .replace(R.id.frame, AddFragment())
                    .addToBackStack(null)
                    .commit()
        }

        return view
    }


}
