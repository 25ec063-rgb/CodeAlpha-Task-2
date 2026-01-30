package com.example.flashcardquizapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateFlashcardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_flashcard)

        val questionEt = findViewById<EditText>(R.id.question)
        val answerEt = findViewById<EditText>(R.id.answer)
        val saveBtn = findViewById<Button>(R.id.saveFlashcard)

        FlashcardStore.load(this)

        saveBtn.setOnClickListener {
            val qText = questionEt.text.toString().trim()
            val aText = answerEt.text.toString().trim()

            if (qText.isEmpty() || aText.isEmpty()) {
                Toast.makeText(this, "Enter both question and answer", Toast.LENGTH_SHORT).show()
            } else {
                FlashcardStore.flashcards.add(Flashcard(qText, aText))
                FlashcardStore.save(this)

                questionEt.text.clear()
                answerEt.text.clear()

                Toast.makeText(this, "Flashcard saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
