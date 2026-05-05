package com.shcherbakovnikita.ugadaika

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.shcherbakovnikita.ugadaika.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private  var max = 25
    private var min = 1
    private var popitki = -1
    private var maxPopitki = 6
    private var c = 0

    private lateinit var settingsLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        settingsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK)
            {
                val data = result.data
                max = data?.getIntExtra("MaxChislo", 25) ?: 25
                maxPopitki = data?.getIntExtra("KolvoPopitok", 6) ?: 6
                startGame()
            }
        }

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
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("MaxChislo", max)
            intent.putExtra("KolvoPopitok", maxPopitki)
            settingsLauncher.launch(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun randomChislo(): Int = Random.nextInt(min, max + 1)

    private fun startGame()
    {
    binding.textResult.text = ""
        popitki = maxPopitki
        binding.vvodd.setText("")
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
