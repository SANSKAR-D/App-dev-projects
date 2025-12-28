package com.example.quizzonquizapp.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzonquizapp.R

class LevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val easy : Button = findViewById(R.id.levelEasy)
        val medium : Button = findViewById(R.id.levelMedium)
        val hard : Button = findViewById(R.id.levelHard)

        easy.setOnClickListener {
            Intent(this@LevelActivity, QuestionActivity::class.java).also { startActivity(it) }
            finish()
        }
        medium.setOnClickListener {
            Intent(this@LevelActivity, QuestionActivity2::class.java).also { startActivity(it) }
            finish()
        }
        hard.setOnClickListener {
            Intent(this@LevelActivity, QuestionActivity3::class.java).also { startActivity(it) }
            finish()
        }
    }
}
