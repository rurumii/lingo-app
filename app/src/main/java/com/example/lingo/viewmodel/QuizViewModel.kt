package com.example.lingo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lingo.model.Word

class QuizViewModel: ViewModel() {
    private val wordsList = listOf(
        Word(id = 1, kanji = "猫", translation = "Cat", romaji = "[neko]", imageName = "ic_cat"),
        Word(id = 2, kanji = "犬", translation = "Dog", romaji = "[inu]", imageName = "ic_dog"),
        Word(id = 3, kanji = "桜", translation = "Sakura", romaji = "[sakura]", imageName = "ic_sakura"),
        Word(id = 4, kanji = "鳥", translation = "Bird", romaji = "[tori]", imageName = "ic_bird")
    )

    private val _currentQuestionWord = MutableLiveData<Word>()
    val currentQuestionWord: LiveData<Word> get() = _currentQuestionWord

    private val _options = MutableLiveData<List<String>>()
    val options: LiveData<List<String>> get() = _options

    private var currentQuestionCount = 0
    private val maxQuestions = 4

    var score = 0
        private set

    private val _isQuizFinished = MutableLiveData<Boolean>(false)
    val isQuizFinished: LiveData<Boolean> get() = _isQuizFinished

    init {
        generateNextQuestion()
    }

    private fun generateNextQuestion(){
        val correctWord = wordsList.random()
        _currentQuestionWord.value = correctWord

        val wrongWords = wordsList.filter { it.id != correctWord.id }.shuffled().take(3)

        val allOptions = mutableListOf<String>()
        allOptions.add(correctWord.translation)
        wrongWords.forEach { allOptions.add(it.translation) }

        _options.value = allOptions.shuffled()

        currentQuestionCount++
    }

    fun checkAnswer(selectedAnswer:String) {
        val correctAnswer = currentQuestionWord.value?.translation

        if (selectedAnswer==correctAnswer)
        {
            score++
        }
        if (currentQuestionCount < maxQuestions)
        {
            generateNextQuestion()
        } else {
            _isQuizFinished.value = true
        }
    }
}