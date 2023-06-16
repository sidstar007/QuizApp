package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import android.widget.*

    class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

        private var mUserName :String? = null
        private var mCurrentPosition: Int = 1
        private var mQuestionsList: ArrayList<Question>? = null
        private var mSelectedOptionPosition: Int = 0
        private var progressBar: ProgressBar? = null
        private var disProgress: TextView? = null
        private var tvQuestion: TextView? = null
        private var qImage: ImageView? = null
        private var mCorrectAnswers: Int = 0
        private var op1: TextView? = null
        private var op2: TextView? = null
        private var op3: TextView? = null
        private var op4: TextView? = null
        private var btnSubmit: Button? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_quiz_questions)

            mUserName = intent.getStringExtra(Constants.user_name)
            progressBar = findViewById(R.id.progressbar)
            disProgress = findViewById(R.id.disProgress)
            tvQuestion = findViewById(R.id.tvquestion)
            qImage = findViewById(R.id.qimage)
            op1 = findViewById(R.id.op1)
            op2 = findViewById(R.id.op2)
            op3 = findViewById(R.id.op3)
            op4 = findViewById(R.id.op4)
            btnSubmit = findViewById(R.id.btnsubmit)

            op1?.setOnClickListener(this)
            op2?.setOnClickListener(this)
            op3?.setOnClickListener(this)
            op4?.setOnClickListener(this)
            btnSubmit?.setOnClickListener(this)

            mQuestionsList = Constants.getQuestions()
            setQuestion()
        }

        private fun setQuestion() {
            val questionsList = Constants.getQuestions()
            Log.i("QuestionsList size", "${questionsList.size}")

            for (i in questionsList) {
                Log.e("Question", i.question)
            }

            val question: Question = mQuestionsList!![mCurrentPosition - 1]
            qImage?.setImageResource(question.image)
            progressBar?.progress = mCurrentPosition
            disProgress?.text = "$mCurrentPosition/${progressBar?.max}"
            tvQuestion?.text = question.question
            op1?.text = question.op1
            op2?.text = question.op2
            op3?.text = question.op3
            op4?.text = question.op4

            if (mCurrentPosition == mQuestionsList!!.size) {
                btnSubmit?.text = "FINISH"
            } else {
                btnSubmit?.text = "SUBMIT"
            }
        }




            private fun defaultOptionsView() {
                val options = ArrayList<TextView>()
                op1?.let {
                    options.add(0, it)
                }
                op2?.let {
                    options.add(1, it)
                }
                op3?.let {
                    options.add(2, it)
                }
                op4?.let {
                    options.add(3, it)
                }
                for (option in options) {
                    option.setTextColor(Color.parseColor("#7A8089"))
                    option.typeface = Typeface.DEFAULT
                    option.background =
                        ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                }
            }

            private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
                defaultOptionsView()
                mSelectedOptionPosition = selectedOptionNum
                tv.setTextColor(Color.parseColor("#363A43"))
                tv.setTypeface(tv.typeface, Typeface.BOLD)
                tv.background =
                    ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
            }

            override fun onClick(view: View) {
                when (view?.id) {
                    R.id.op1 -> {
                        op1?.let {
                            selectedOptionView(it, 1)
                        }
                    }
                    R.id.op2 -> {
                        op2?.let {
                            selectedOptionView(it, 2)
                        }
                    }
                    R.id.op3 -> {
                        op3?.let {
                            selectedOptionView(it, 3)
                        }
                    }
                    R.id.op4 -> {
                        op4?.let {
                            selectedOptionView(it, 4)
                        }
                    }

                    R.id.btnsubmit -> {
                        defaultOptionsView()
                        if (mSelectedOptionPosition == 0) {

                            mCurrentPosition++

                            when {

                                mCurrentPosition <= mQuestionsList!!.size -> {

                                    setQuestion()
                                }
                                else -> {

                                    val intent = Intent(this, result::class.java)
                                    intent.putExtra(Constants.user_name,mUserName)
                                    intent.putExtra(Constants.correct_answers,mCorrectAnswers)
                                    intent.putExtra(Constants.total_questions,mQuestionsList?.size)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        } else {
                            val question = mQuestionsList?.get(mCurrentPosition - 1)

                            // This is to check if the answer is wrong
                            if (question!!.correctAns != mSelectedOptionPosition) {
                                answerView(
                                    mSelectedOptionPosition,
                                    R.drawable.wrong_option_border_bg
                                )
                            }
                            else {
                                mCorrectAnswers++
                            }
                                // This is for correct answer
                                answerView(question.correctAns, R.drawable.correct_option_border_bg)

                            if (mCurrentPosition == mQuestionsList!!.size) {
                                btnSubmit?.text = "FINISH"
                            } else {
                                btnSubmit?.text = "GO TO NEXT QUESTION"
                            }
                            mSelectedOptionPosition = 0

                        }
                    }
                }
            }






            private fun answerView(answer: Int, drawableView: Int) {
                when (answer) {
                    1 -> {
                        op1?.background =
                            ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
                    }
                    2 -> {
                        op2?.background =
                            ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
                    }
                    3 -> {
                        op3?.background =
                            ContextCompat.getDrawable((this@QuizQuestionsActivity), drawableView)
                    }
                    4 -> {
                        op4?.background =
                            ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
                    }
                }
            }
        }



