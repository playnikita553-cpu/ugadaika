package com.shcherbakovnikita.ugadaika

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shcherbakovnikita.ugadaika.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity()
{
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        var maxChislo = args?.getInt("MaxChislo") ?: 25
        var kolvoPopitok = args?.getInt("KolvoPopitok") ?: 6

        obnovitPolya(maxChislo, kolvoPopitok)

        binding.btnSohranit.setOnClickListener()
        {
            if (binding.maxChislo.text.toString() != "" && binding.kolvoPopitok.text.toString() != "")
            {
                maxChislo = binding.maxChislo.text.toString().toInt()
                kolvoPopitok = binding.kolvoPopitok.text.toString().toInt()

                val intent = Intent()
                intent.putExtra("MaxChislo", maxChislo)
                intent.putExtra("KolvoPopitok", kolvoPopitok)
                setResult(RESULT_OK, intent)
                finish()
            }
            else
            {
                Toast.makeText(this, "Не оставляйте поля пустыми!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun obnovitPolya(maxChislo: Int, kolvoPopitok: Int)
    {
        binding.maxChislo.setText(maxChislo.toString())
        binding.kolvoPopitok.setText(kolvoPopitok.toString())
    }
}
