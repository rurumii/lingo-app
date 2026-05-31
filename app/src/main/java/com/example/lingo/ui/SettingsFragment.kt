package com.example.lingo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lingo.R

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<Button>(R.id.btnBackFromSettings)
        val switchSound = view.findViewById<Switch>(R.id.switchSound)
        val switchNotifications = view.findViewById<Switch>(R.id.switchNotifications)

        val sharedPreferences = requireContext().getSharedPreferences("LingoPrefs", Context.MODE_PRIVATE)

        switchSound.isChecked = sharedPreferences.getBoolean("setting_sound", true)
        switchNotifications.isChecked = sharedPreferences.getBoolean("setting_notifications", true)

        switchSound.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("setting_sound", isChecked).apply()
        }

        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("setting_notifications", isChecked).apply()
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}