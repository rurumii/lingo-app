package com.example.lingo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lingo.R
import android.content.Context

class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score = arguments?.getInt("score") ?: 0

        val tvFinalScore = view.findViewById<TextView>(R.id.tvFinalScore)
        val tvSubtitle = view.findViewById<TextView>(R.id.tvSubtitle)
        val btnBackToMenu = view.findViewById<Button>(R.id.btnBackToMenu)

        tvFinalScore.text = "$score / 4"

        val sharedPreferences = requireContext().getSharedPreferences("LingoPrefs", Context.MODE_PRIVATE)

        val savedHighScore = sharedPreferences.getInt("high_score", 0)

        if (score > savedHighScore) {
            sharedPreferences.edit().putInt("high_score", score).apply()

            tvSubtitle.text = "New Best Score! "
        } else {
            tvSubtitle.text = "Best Score: $savedHighScore / 4"
        }

        btnBackToMenu.setOnClickListener {
            findNavController().navigate(R.id.action_score_to_home)
        }
    }
}