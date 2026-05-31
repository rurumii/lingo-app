package com.example.lingo.viewmodel

import android.R
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lingo.model.Word

class LessonViewModel : ViewModel() {

    val wordsList = listOf(
        Word(id = 1, kanji = "猫", translation = "Cat", romaji = "[neko]", imageName = "ic_cat2"),
        Word(id = 2, kanji = "犬", translation = "Dog", romaji = "[inu]", imageName = "ic_dog"),
        Word(id = 3, kanji = "桜", translation = "Sakura", romaji = "[sakura]", imageName = "ic_sakura"),
        Word(id = 4, kanji = "鳥", translation = "Bird", romaji = "[tori]", imageName = "ic_bird")
    )

    // start from zero index
    private var currentIndex = 0;

    // _currentWord - may be changed; currentWord - readonly
    private val _currentWord = MutableLiveData<Word>()
    val currentWord: LiveData<Word> get() = _currentWord

    private val _isLastWord = MutableLiveData<Boolean>(false)
    val isLastWord: LiveData<Boolean> get() = _isLastWord


    init {
        // init block is triggered once when ViewModel is created
        // immediately throw the first word from the list into LiveData
        _currentWord.value = wordsList[currentIndex]
    }

    fun nextWord(){
        if (currentIndex < wordsList.size -1) {
            currentIndex++
            _currentWord.value = wordsList[currentIndex]

            if (currentIndex == wordsList.size -1) {
                _isLastWord.value = true;
            }
        }
    }


}