package com.example.flashcardquizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTv: TextView
    private lateinit var answerTv: TextView
    private lateinit var actionBtn: Button

    private var currentIndex = 0
    private var isAnswerVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTv = findViewById(R.id.questionText)
        answerTv = findViewById(R.id.answerText)
        actionBtn = findViewById(R.id.actionBtn)

        if (FlashcardStore.flashcards.isEmpty()) {
            Toast.makeText(this, "No flashcards available", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        showFlashcard()

        actionBtn.setOnClickListener {
            if (!isAnswerVisible) {
                // Show Answer
                answerTv.visibility = TextView.VISIBLE
                actionBtn.text = "Next"
                isAnswerVisible = true
            } else {
                // Move to Next Question
                currentIndex++
                if (currentIndex >= FlashcardStore.flashcards.size) {
                    currentIndex = 0
                    Toast.makeText(this, "Restarting quiz", Toast.LENGTH_SHORT).show()
                }
                showFlashcard()
                isAnswerVisible = false
                answerTv.visibility = TextView.GONE
                actionBtn.text = "Show Answer"
            }
        }
    }

    private fun showFlashcard() {
        val flashcard = FlashcardStore.flashcards[currentIndex]
        questionTv.text = flashcard.question
        answerTv.text = flashcard.answer
    }
}

