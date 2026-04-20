package com.shcherbakovnikita.ugadaika

import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import android.view.View
import android.widget.EditText
import android.widget.Toast
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    startGame()

    binding.proverka.setOnClickListener()
    {


        proverkaChisel()

    }

    binding.newGame.setOnClickListener()
    {
        startGame()
        binding.proverka.visibility = View.VISIBLE
    }










    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(R.id.settings == item.itemId){
            Toast.makeText(this, "settings", Toast.LENGTH_SHORT)
        }
        return super.onOptionsItemSelected(item)
    }









    private fun randomChislo(): Int = Random.nextInt(min, max)

    private fun startGame()
    {
    binding.textResult.text = ""
        popitki = maxPopitki
        binding.vvodd.text.toString()
        binding.vvodd.hint = "Введите число от $min, до $max"
        binding.textResult.text = ""
        binding.popitki.text = "Попыток: $popitki"
        c = randomChislo()
        binding.imagee.visibility = View.INVISIBLE
        binding.newGame.visibility = View.INVISIBLE

    }
    private fun proverkaChisel() {
        val userNumber = getUserNumber()


        if (userNumber == -1) {
            binding.textResult.setText("Введите число!")
            binding.vvodd.setText("")
            return
        }

        if (userNumber == c) {
            win()
        }
        else
            if (userNumber < c) {
            binding.textResult.setText("Нужно число больше")
        }
        else
            if (userNumber > c) {
            binding.textResult.setText("Нужно число меньше")
        }

        popitki--
        binding.popitki.text = "Осталось попыток: $popitki"

        if (popitki <= 0) fail()

        binding.vvodd.setText("")
    }

    private fun win()
    {
        binding.imagee.visibility = View.VISIBLE


        binding.newGame.visibility = View.VISIBLE

        binding.textResult.text = "Победа:]"

        binding.imagee.setImageResource(R.drawable.win)
        binding.proverka.visibility = View.INVISIBLE




    }
    private fun fail()
    {
        binding.imagee.visibility = View.VISIBLE
        binding.newGame.visibility = View.VISIBLE


        binding.textResult.text = "Поражение:[ (правильное число было: ${c})"

        binding.imagee.setImageResource(R.drawable.lose)
        binding.proverka.visibility = View.INVISIBLE

    }

    private  fun getUserNumber(): Int{
        var a: Int
        try {
            a = binding.vvodd.text.toString().toInt()
        }
        catch (e: Exception){
            a = -1
        }
        return a
    }

    }

