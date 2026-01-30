package com.example.flashcardquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnCreate = findViewById<Button>(R.id.btnCreate)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnQuiz = findViewById<Button>(R.id.btnQuiz)

        btnCreate.setOnClickListener {
            startActivity(Intent(this, CreateFlashcardActivity::class.java))
        }

        btnView.setOnClickListener {
            startActivity(Intent(this, SavedFlashcardsActivity::class.java))
        }

        btnQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
    }
}
