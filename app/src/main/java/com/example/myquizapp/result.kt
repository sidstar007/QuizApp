package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val disName : TextView = findViewById(R.id.disName)
        val disScore : TextView = findViewById(R.id.disScore)
        val btnFinish: Button = findViewById(R.id.finish)

        disName.text=intent.getStringExtra(Constants.user_name)
        val correctAns = intent.getIntExtra(Constants.correct_answers,0)
        val totalQuestions = intent.getIntExtra(Constants.total_questions,0)

        disScore.text = "Your score is $correctAns/$totalQuestions"

        btnFinish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}