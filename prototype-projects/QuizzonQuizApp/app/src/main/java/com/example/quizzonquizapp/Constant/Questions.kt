package com.example.quizzonquizapp.Constant

import com.example.quizzonquizapp.Model.Question
import com.example.quizzonquizapp.R

object Questions {

    const val TotalQuestion = "10"
    const val User_Name = "user_name"
    const val Score = "score"
    fun getQuestions(): MutableList<Question>{
        val questions = mutableListOf<Question>()

        val q1 = Question(1,
            image = R.drawable.qe1,"Q1. Guess the Symbol ?","Python","Java","C++","Kotlin",1)
        questions.add(q1)
        val q2 = Question(2,
            image = R.drawable.qe2,"Q2. Guess the Symbol ?","Python","Java","C","Kotlin",3)
        questions.add(q2)
        val q3 = Question(3,
            image = R.drawable.qe3,"Q3. Guess the Symbol ?","Python","Java","Swift","Kotlin",3)
        questions.add(q3)
        val q4 = Question(4,
            image = R.drawable.qe4,"Q4. Guess the Symbol ?","Python","Java","C++","Kotlin",2)
        questions.add(q4)
        val q5 = Question(5,
            image = R.drawable.qe5,"Q5. Guess the Symbol ?","Python","Java","C++","Kotlin",4)
        questions.add(q5)
        val q6 = Question(6,
            image = R.drawable.qe6,"Q1. Guess the Profession ?","Software engineer","Mechanical engineer","Civil engineer","Electronic engineer",1)
        questions.add(q6)
        val q7 = Question(7,
            image = R.drawable.qe7,"Q2. Guess the Profession ?","Software engineer","Mechanical engineer","Civil engineer","Electronic engineer",4)
        questions.add(q7)
        val q8 = Question(8,
            image = R.drawable.qe8,"Q3. Guess the Profession ?","Software engineer","Mechanical engineer","Civil engineer","Electronic engineer",3)
        questions.add(q8)
        val q9 = Question(9,
            image = R.drawable.qe9,"Q4. Guess the Profession ?","Momos stall","Pani Puri stall","StartUp","Engineer",4)
        questions.add(q9)
        val q10 = Question(10,
            image = R.drawable.qe10,"Q5. Guess the Profession ?","Pani Puri stall","Momos stall","Engineer","StartUp",3)
        questions.add(q10)
        val q11 = Question(11,
            image = R.drawable.qe11,"Q1. Calculate the Answer","2πi(e\n" +
                    "−z" +
                    "1" +
                    "\t\u200B" +
                    "" +
                    "/(4z" +
                    "1" +
                    "3" +
                    "\t\u200B" +
                    "" +
                    ")+e" +
                    "−z" +
                    "2" +
                    "\t\u200B" +
                    "" +
                    "/(4z" +
                    "2" +
                    "3" +
                    "\t\u200B" +
                    "" +
                    "))","2πi(e" +
                    "−z" +
                    "1" +
                    "\t\u200B" +
                    "" +
                    "/(3z" +
                    "1" +
                    "3" +
                    "\t\u200B" +
                    "" +
                    ")+e" +
                    "−z" +
                    "2" +
                    "\t\u200B" +
                    "" +
                    "/(3z" +
                    "2" +
                    "3" +
                    "\t\u200B" +
                    "" +
                    "))","0","None of these",1)
        questions.add(q11)
        val q12 = Question(12,
            image = R.drawable.qe12,"Q2. Calculate the Answer",
                    "0.57721","0.58821","0.56671","0",1)
        questions.add(q12)
        val q13 = Question(13,
            image = R.drawable.qe13,"Q3. Calculate the Answer","51","50","100","25",2)
        questions.add(q13)
        val q14 = Question(14,
            image = R.drawable.qe14,"Q4. Calculate the Answer","1","2","5","All of these",4)
        questions.add(q14)
        val q15 = Question(15,
            image = R.drawable.qe15,"Q5. Calculate the Answer","40","49","50","59",2)
        questions.add(q15)
        return questions
    }
}