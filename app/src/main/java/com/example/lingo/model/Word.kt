package com.example.lingo.model

data class Word(
    val id: Int,
    val kanji: String,
    val translation: String,
    val romaji: String,
    val imageName: String
)