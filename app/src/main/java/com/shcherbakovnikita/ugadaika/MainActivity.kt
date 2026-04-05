package com.shcherbakovnikita.ugadaika

import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle

import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.viewmodel.CreationExtras
import com.shcherbakovnikita.ugadaika.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private  var max = 25
    private var min = 1
    private var popitki = -1
    private var maxPopitki = 6
    private var c = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    startGame()

    binding.proverka.setOnClickListener()
    {

        if((popitki != 0) and (binding.proverka.text.toString() != "") and (binding.textResult.text != "Победа:]") )
        {
        proverkaChisel()
        }
    }

    binding.newGame.setOnClickListener()
    {
        startGame()
    }










    }

    private fun randomChislo(): Int = Random.nextInt(min, max)

    private fun startGame()
    {
    binding.textResult.setText("")
        var vvod: EditText = binding.vvodd
        popitki = maxPopitki
        vvod.text.toString()
        vvod.setHint("Введите число от $min, до $max")
        binding.textResult.setText("")
        binding.popitki.setText("Попыток: $popitki")
        c = randomChislo()
        binding.imagee.visibility = View.INVISIBLE
        binding.newGame.visibility = View.INVISIBLE

    }
    private fun proverkaChisel()
    {

       if ((binding.vvodd.text.toString().toInt() == c))
       {
           win()
       }
       else
           if(binding.vvodd.text.toString().toInt() < c) binding.textResult.setText("Нужно число больше")

           else
               if(binding.vvodd.text.toString().toInt() > c) binding.textResult.setText("Нужно число меньше")

        popitki--


        binding.popitki.setText("Осталось попыток: $popitki")


        if(popitki <= 0) fail()



        binding.vvodd.setText("")

    }

    private fun win()
    {
        binding.imagee.visibility = View.VISIBLE


        binding.newGame.visibility = View.VISIBLE

        binding.textResult.setText("Победа:]")

        binding.imagee.setImageResource(R.drawable.win)





    }
    private fun fail()
    {
        binding.imagee.visibility = View.VISIBLE
        binding.newGame.visibility = View.VISIBLE


        binding.textResult.setText("Поражение:[ (правильное число было: ${randomChislo()})")

        binding.imagee.setImageResource(R.drawable.lose)


    }


    }

