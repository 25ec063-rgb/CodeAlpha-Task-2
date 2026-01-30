package com.example.flashcardquizapp

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object FlashcardStore {

    private const val PREF_NAME = "flashcard_prefs"
    private const val KEY_FLASHCARDS = "flashcards"

    val flashcards: MutableList<Flashcard> = mutableListOf()

    fun load(context: Context) {
        flashcards.clear()
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val jsonString = prefs.getString(KEY_FLASHCARDS, null) ?: return

        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val question = obj.getString("question")
            val answer = obj.getString("answer")
            flashcards.add(Flashcard(question, answer))
        }
    }

    fun save(context: Context) {
        val jsonArray = JSONArray()
        for (card in flashcards) {
            val obj = JSONObject()
            obj.put("question", card.question)
            obj.put("answer", card.answer)
            jsonArray.put(obj)
        }

        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_FLASHCARDS, jsonArray.toString()).apply()
    }
}


