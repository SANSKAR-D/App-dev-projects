package com.example.quizzonquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzonquizapp.Constant.Questions
import com.example.quizzonquizapp.UI.LevelActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val startButton : Button = findViewById(R.id.s1)
        val inputName : EditText = findViewById(R.id.name)

        startButton.setOnClickListener {
            if(!inputName.text.isEmpty()){
                Intent(this@MainActivity, LevelActivity::class.java).also{
                    it.putExtra(Questions.User_Name,inputName.text.toString())
                    startActivity(it)
                }
                finish()
            }else{
                Toast.makeText(this@MainActivity, "Name field is empty !!", Toast.LENGTH_LONG).show()
            }
        }
    }
}