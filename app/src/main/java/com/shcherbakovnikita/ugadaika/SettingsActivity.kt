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

        binding.maxChislo.setText(maxChislo.toString())
        
        binding.kolvoPopitok.setText(kolvoPopitok.toString())

        binding.btnSohranit.setOnClickListener(){
            val textMax = binding.maxChislo.text.toString()

            
            val textPopitki = binding.kolvoPopitok.text.toString()
            

            if (textMax.isEmpty() || textPopitki.isEmpty())
            {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                
                return@setOnClickListener
            }

            maxChislo = textMax.toInt()
            
            kolvoPopitok = textPopitki.toInt()

            val intent = Intent()
            
            intent.putExtra("MaxChislo", maxChislo)
            
            intent.putExtra("KolvoPopitok", kolvoPopitok)
            
            setResult(RESULT_OK, intent)
            
            finish()
        }
    }
}
