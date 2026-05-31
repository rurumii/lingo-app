package com.example.lingo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lingo.R
import com.example.lingo.viewmodel.LessonViewModel

class CardFragment : Fragment() {

    private lateinit var viewModel: LessonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LessonViewModel::class.java)

        val tvKanji = view.findViewById<TextView>(R.id.tvKanji)
        val tvRomaji = view.findViewById<TextView>(R.id.tvRomaji)
        val tvTranslation = view.findViewById<TextView>(R.id.tvTranslation)
        val ivWordImage = view.findViewById<ImageView>(R.id.ivWordImage)
        val btnNext = view.findViewById<Button>(R.id.btnNext)

        viewModel.currentWord.observe(viewLifecycleOwner) { word ->
            tvKanji.text = word.kanji
            tvRomaji.text = word.romaji
            tvTranslation.text = word.translation

            val imageResId = resources.getIdentifier(word.imageName, "drawable", requireActivity().packageName)

            if (imageResId != 0) {
                ivWordImage.setImageResource(imageResId)
            } else {
                ivWordImage.setImageResource(R.drawable.ic_cat)
            }
        }

        viewModel.isLastWord.observe(viewLifecycleOwner) { isLast ->
            if (isLast) {
                btnNext.text = "Go to Quiz"
            }
        }

        btnNext.setOnClickListener {
            if (viewModel.isLastWord.value == true) {
                findNavController().navigate(R.id.action_card_to_quiz)
            } else {
                viewModel.nextWord()
            }
        }
    }
}