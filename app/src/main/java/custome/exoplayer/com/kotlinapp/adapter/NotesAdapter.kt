package custome.exoplayer.com.kotlinapp.adapter

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import custome.exoplayer.com.kotlinapp.R
import custome.exoplayer.com.kotlinapp.model.Note
import custome.exoplayer.com.kotlinapp.view.MainFragment
import custome.exoplayer.com.kotlinapp.view.UpdateFragment
import kotlinx.android.synthetic.main.note_view.view.*

class NotesAdapter(private val mainFragment: MainFragment) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    var notes: List<Note> = arrayListOf()

    fun setNotes(notes: ArrayList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_view, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, i: Int) {
        holder.title.text = notes[i].title
        holder.note.text = notes[i].note
        holder.itemView.setOnClickListener {
            val updateFragment = UpdateFragment()
            val bundle = Bundle()
            bundle.putString("id", notes[i]._id)
            updateFragment.arguments = bundle
            mainFragment.fragmentManager?.beginTransaction()?.addToBackStack(null)
                    ?.setCustomAnimations(R.anim.design_bottom_sheet_slide_in, 0, 0, R.anim.design_bottom_sheet_slide_out)
                    ?.replace(R.id.frame, updateFragment)?.commit()
        }
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.tv_note_title
        val note: TextView = view.tv_note_data
    }
}