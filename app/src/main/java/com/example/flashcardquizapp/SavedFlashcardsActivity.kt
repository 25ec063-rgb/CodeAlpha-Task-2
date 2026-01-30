package com.example.flashcardquizapp

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class SavedFlashcardsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_flashcards)

        FlashcardStore.load(this)

        val listView = findViewById<ListView>(R.id.listView)
        val adapter = FlashcardAdapter(this, FlashcardStore.flashcards)
        listView.adapter = adapter
    }
}

