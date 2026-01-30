package com.example.flashcardquizapp

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.json.JSONArray
import org.json.JSONObject

class FlashcardAdapter(
    private val context: Context,
    private val flashcards: MutableList<Flashcard>
) : BaseAdapter() {

    override fun getCount(): Int = flashcards.size

    override fun getItem(position: Int): Any = flashcards[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_flashcard, parent, false)

        val questionTv = view.findViewById<TextView>(R.id.itemQuestion)
        val answerTv = view.findViewById<TextView>(R.id.itemAnswer)
        val editBtn = view.findViewById<Button>(R.id.editBtn)
        val deleteBtn = view.findViewById<Button>(R.id.deleteBtn)

        val flashcard = flashcards[position]
        questionTv.text = flashcard.question
        answerTv.text = flashcard.answer

        editBtn.setOnClickListener {
            showEditDialog(position)
        }

        deleteBtn.setOnClickListener {
            flashcards.removeAt(position)
            FlashcardStore.save(context)
            notifyDataSetChanged()
        }

        return view
    }

    private fun showEditDialog(position: Int) {
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_edit_flashcard, null)

        val qEt = dialogView.findViewById<EditText>(R.id.editQuestion)
        val aEt = dialogView.findViewById<EditText>(R.id.editAnswer)

        qEt.setText(flashcards[position].question)
        aEt.setText(flashcards[position].answer)

        AlertDialog.Builder(context)
            .setTitle("Edit Flashcard")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                flashcards[position].question = qEt.text.toString()
                flashcards[position].answer = aEt.text.toString()
                FlashcardStore.save(context)
                notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
