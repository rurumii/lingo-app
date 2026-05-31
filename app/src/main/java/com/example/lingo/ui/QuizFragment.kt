package com.example.lingo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lingo.R
import com.example.lingo.viewmodel.QuizViewModel
import com.example.lingo.model.Word
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import android.content.Context

class QuizFragment : Fragment() {

    private  lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        val tvQuizQuestion = view.findViewById<TextView>(R.id.tvQuizQuestion)
        val btnOption1 = view.findViewById<Button>(R.id.btnOption1)
        val btnOption2 = view.findViewById<Button>(R.id.btnOption2)
        val btnOption3 = view.findViewById<Button>(R.id.btnOption3)
        val btnOption4 = view.findViewById<Button>(R.id.btnOption4)

        val sharedPreferences = requireContext().getSharedPreferences("LingoPrefs", Context.MODE_PRIVATE)
        val isSoundEnabled = sharedPreferences.getBoolean("setting_sound", true)

        Toast.makeText(requireContext(), "Sound enabled: $isSoundEnabled", Toast.LENGTH_SHORT).show()


        btnOption1.isSoundEffectsEnabled = isSoundEnabled
        btnOption1.isHapticFeedbackEnabled = isSoundEnabled

        btnOption2.isSoundEffectsEnabled = isSoundEnabled
        btnOption2.isHapticFeedbackEnabled = isSoundEnabled

        btnOption3.isSoundEffectsEnabled = isSoundEnabled
        btnOption3.isHapticFeedbackEnabled = isSoundEnabled

        btnOption4.isSoundEffectsEnabled = isSoundEnabled
        btnOption4.isHapticFeedbackEnabled = isSoundEnabled
        // -------------------------------------

        viewModel.currentQuestionWord.observe(viewLifecycleOwner) { currentWord ->
            tvQuizQuestion.text = currentWord.kanji
        }

        viewModel.isQuizFinished.observe(viewLifecycleOwner) {isFinished ->
            if (isFinished)
            {
                val bundle = Bundle()
                bundle.putInt("score", viewModel.score)
                findNavController().navigate(R.id.action_quiz_to_score, bundle)
            }
        }

        viewModel.options.observe(viewLifecycleOwner) { optionsList ->
            btnOption1.text = optionsList[0]
            btnOption2.text = optionsList[1]
            btnOption3.text = optionsList[2]
            btnOption4.text = optionsList[3]
        }

        val clickListener = View.OnClickListener{ clickedView ->
            val clickedButton = clickedView as Button
            val selectedAnswer = clickedButton.text.toString()

            viewModel.checkAnswer(selectedAnswer)
        }

        btnOption1.setOnClickListener(clickListener)
        btnOption2.setOnClickListener(clickListener)
        btnOption3.setOnClickListener(clickListener)
        btnOption4.setOnClickListener(clickListener)
    }
}