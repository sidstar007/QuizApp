package com.example.myquizapp

object Constants {

    const val user_name : String = "user_name"
    const val total_questions :String = "total_questions"
    const val correct_answers: String = "correct_answer"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val q1 = Question(
            1,
            "Name of the following animal is",
            R.drawable.tiger,
            "Leopard",
            "Tiger",
            "Cheetah",
            "Panther",
            2
        )
        questionsList.add(q1)

        val q2 = Question(
            2,"Who is this person?",R.drawable.elon,"Mahatma Gandhi","Thanos","Tony Stark","Elon Musk",4
        )
        questionsList.add(q2)

        val q3 = Question(
            3,"Which company makes the following video game?",R.drawable.csgo,"Valve Corporation","Rockstar Games","Ubisoft","EA",1
        )
        questionsList.add(q3)

        val q4 = Question(
            4,"Which country does this flag belong to?",R.drawable.gabon,"Nigeria","Somalia","Switzerland","Gabon",4
        )
        questionsList.add(q4)

        val q5 = Question(
            5,"What's the name of this place?",R.drawable.miami,"Goa","Brazil","Miami","Mumbai",2
        )
        questionsList.add(q5)

        return questionsList
    }
}