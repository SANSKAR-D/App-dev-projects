package com.example.quizzonquizapp.UI

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzonquizapp.Constant.Questions
import com.example.quizzonquizapp.Model.Question
import com.example.quizzonquizapp.R

class QuestionActivity3 : AppCompatActivity(), View.OnClickListener{
    private lateinit var questionlist : MutableList<Question>

    private var name : String = ""
    private lateinit var progressBar: ProgressBar
    private lateinit var textProgress : TextView
    private lateinit var questionImageView: ImageView
    private lateinit var question: TextView
    private lateinit var option1 : TextView
    private lateinit var option2 : TextView
    private lateinit var option3 : TextView
    private lateinit var option4 : TextView
    private lateinit var checkButton: Button
    private var currentPosition = 1
    private var selectedOption = 0
    private var score = 0
    private var answered = false
    private var isLast = false;
    private lateinit var currentQuestion : Question
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        name = intent.getStringExtra(Questions.User_Name)!!
        questionlist = Questions.getQuestions()
        Log.d("hiiiiiiiii","${questionlist.size}")
        progressBar = findViewById(R.id.progress)
        textProgress = findViewById(R.id.progressq)
        questionImageView = findViewById(R.id.qimage)
        question = findViewById(R.id.question)
        option1 = findViewById(R.id.o1)
        option2 = findViewById(R.id.o2)
        option3 = findViewById(R.id.o3)
        option4 = findViewById(R.id.o4)
        option1.setOnClickListener (this)
        option2.setOnClickListener (this)
        option3.setOnClickListener (this)
        option4.setOnClickListener (this)
        checkButton = findViewById(R.id.check)
        checkButton.setOnClickListener (this)
        setQuestion()
    }
    private fun setQuestion(){
        val q = questionlist[10 + currentPosition-1]
        questionImageView.setImageResource(q.image)
        question.text = q.question
        progressBar.progress = currentPosition
        textProgress.text = "$currentPosition/${progressBar.max}"
        option1.text = q.option1
        option2.text = q.option2
        option3.text = q.option3
        option4.text = q.option4
        currentQuestion = q
        if(currentPosition < 5) {
            currentPosition++
        }
        answered = false
        resetQuestion()
    }
    private fun resetQuestion(){
        val option = mutableListOf<TextView>()
        option.add(option1)
        option.add(option2)
        option.add(option3)
        option.add(option4)
        for(options in option){
            options.setTextColor(Color.parseColor("#FFFFFF"))
            options.typeface = Typeface.DEFAULT
            options.background = ContextCompat.getDrawable(
                this,
                R.drawable.optionbg
            )
        }
    }
    private fun setOption(textView: TextView,selectedOpt : Int){
        resetQuestion()
        selectedOption = selectedOpt
        textView.setTextColor(Color.parseColor("#FFFFFF"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this,R.drawable.selectoptionbg)
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.o1 -> setOption(option1,1)
            R.id.o2 -> setOption(option2,2)
            R.id.o3 -> setOption(option3,3)
            R.id.o4 -> setOption(option4,4)
            R.id.check -> {
                if(!answered){
                    checkQuestion()
                }else{
                    if(currentPosition <= 5 && !isLast) {
                        if(currentPosition == 5) isLast = true
                        setQuestion()
                        checkButton.text = "Check"
                    }else{
                        Intent(this@QuestionActivity3, FinalActivity::class.java).also {
                            it.putExtra(Questions.User_Name,name)
                            it.putExtra(Questions.TotalQuestion,5)
                            it.putExtra(Questions.Score,score)
                            startActivity(it)
                        }
                        finish()
                    }
                }
            }
        }
    }
    private fun checkQuestion(){
        answered = true
        if(selectedOption == currentQuestion.correct){
            score++
            correctAnswer(selectedOption)
        }else{
            when(selectedOption){
                1 -> option1.background = ContextCompat.getDrawable(this,R.drawable.wrongoptionbg)
                2 -> option2.background = ContextCompat.getDrawable(this,R.drawable.wrongoptionbg)
                3 -> option3.background = ContextCompat.getDrawable(this,R.drawable.wrongoptionbg)
                4 -> option4.background = ContextCompat.getDrawable(this,R.drawable.wrongoptionbg)
            }
        }
        if(currentPosition <=5 && !isLast) checkButton.text = "Next"
        else checkButton.text = "Finish"
        correctAnswer(currentQuestion.correct)
    }
    private fun correctAnswer(ans : Int){
        when(ans){
            1 -> option1.background = ContextCompat.getDrawable(this,R.drawable.correctoptionbg)
            2 -> option2.background = ContextCompat.getDrawable(this,R.drawable.correctoptionbg)
            3 -> option3.background = ContextCompat.getDrawable(this,R.drawable.correctoptionbg)
            4 -> option4.background = ContextCompat.getDrawable(this,R.drawable.correctoptionbg)
        }
    }
}