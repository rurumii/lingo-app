package com.example.lingo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lingo.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStart = view.findViewById<Button>(R.id.btnStartCourse)
        val btnSettings = view.findViewById<Button>(R.id.btnSettings)
        val btnAbout = view.findViewById<Button>(R.id.btnAbout)
        val tvHighScoreDisplay = view.findViewById<TextView>(R.id.tvHighScoreDisplay)

        val sharedPreferences = requireContext().getSharedPreferences("LingoPrefs", Context.MODE_PRIVATE)
        val savedHighScore = sharedPreferences.getInt("high_score", 0)

        tvHighScoreDisplay.text = "Best Score: $savedHighScore / 4"

        btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_card)
        }

        btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_settings)
        }

        btnAbout.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_about)
        }
    }
}