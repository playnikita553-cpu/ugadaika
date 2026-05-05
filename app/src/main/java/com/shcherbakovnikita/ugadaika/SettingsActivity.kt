package com.shcherbakovnikita.ugadaika

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shcherbakovnikita.ugadaika.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSettings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.settings_title)

        binding.toolbarSettings.setNavigationOnClickListener { finish() }

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val currentPopitki = prefs.getInt(KEY_MAX_POPITKI, DEFAULT_POPITKI)
        val currentMaxNumber = prefs.getInt(KEY_MAX_NUMBER, DEFAULT_MAX_NUMBER)

        binding.editPopitki.setText(currentPopitki.toString())
        binding.editMaxNumber.setText(currentMaxNumber.toString())

        binding.btnSave.setOnClickListener {
            val newPopitki = binding.editPopitki.text.toString().toIntOrNull()
            val newMaxNumber = binding.editMaxNumber.text.toString().toIntOrNull()

            if (newPopitki == null || newPopitki < 1) {
                Toast.makeText(this, getString(R.string.settings_error_popitki), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (newMaxNumber == null || newMaxNumber < 2) {
                Toast.makeText(this, getString(R.string.settings_error_max_number), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            prefs.edit()
                .putInt(KEY_MAX_POPITKI, newPopitki)
                .putInt(KEY_MAX_NUMBER, newMaxNumber)
                .apply()

            Toast.makeText(this, getString(R.string.settings_saved), Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        }
    }

    companion object {
        const val PREFS_NAME = "ugadaika_settings"
        const val KEY_MAX_POPITKI = "max_popitki"
        const val KEY_MAX_NUMBER = "max_number"
        const val DEFAULT_POPITKI = 6
        const val DEFAULT_MAX_NUMBER = 25
    }
}
