package com.example.quizzonquizapp.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzonquizapp.Constant.Questions
import com.example.quizzonquizapp.MainActivity
import com.example.quizzonquizapp.R
import com.google.android.material.color.utilities.Score

class FinalActivity : AppCompatActivity() {
    private var User : String = ""
    private var marks = 0
    private var totalmarks = 5
    private lateinit var head : TextView
    private lateinit var img : ImageView
    private lateinit var message : TextView
    private lateinit var button: Button
    private lateinit var score : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_final)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        User = intent.getStringExtra(Questions.User_Name)!!
        marks = intent.getIntExtra(Questions.Score,0)
        totalmarks = intent.getIntExtra(Questions.TotalQuestion,5)
        head = findViewById(R.id.head)
        img = findViewById(R.id.trophy)
        message = findViewById(R.id.message1)
        button = findViewById(R.id.end)
        score = findViewById(R.id.score)

        head.text = getString(R.string.congratulations, User)
        score.text = "Your Score : ${marks}/${totalmarks}"
        if(marks <= 2){
            img.setImageResource(R.drawable.brokent)
        }else{
            message.text = "Nice Performance !"
            if(marks == 5){
                message.text = "Perfect Score !!"
            }
        }
        button.setOnClickListener {
            Intent(this@FinalActivity, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }
}