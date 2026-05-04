package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    data class Question(
        val text: String,
        val isHack: Boolean,
        val explanation: String
    )

    private val questions = listOf(
        Question("Drinking coffee helps you sleep better.", false, "Caffeine is a stimulant and keeps you awake."),
        Question("Turning off notifications improves focus.", true, "Less distraction helps you focus better."),
        Question("Cracking knuckles causes arthritis.", false, "No scientific evidence it causes arthritis."),
        Question("Writing tasks down helps you remember them.", true, "Writing reinforces memory."),
        Question("Wait 30 mins after eating before swimming.", false, "This is a myth; it's not dangerous.")
    )

    private var currentIndex = 0
    private var score = 0

    private lateinit var questionNumberText: TextView
    private lateinit var questionText: TextView
    private lateinit var hackButton: Button
    private lateinit var mythButton: Button
    private lateinit var nextButton: Button
    private lateinit var feedbackText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        questionNumberText = findViewById(R.id.questionNumber)
        questionText = findViewById(R.id.questionText)
        hackButton = findViewById(R.id.hackButton)
        mythButton = findViewById(R.id.mythButton)
        nextButton = findViewById(R.id.nextButton)
        feedbackText = findViewById(R.id.feedbackText)

        displayQuestion()

        hackButton.setOnClickListener { checkAnswer(true) }
        mythButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                displayQuestion()
            } else {
                // Show results
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun displayQuestion() {
        val q = questions[currentIndex]
        questionNumberText.text = "Question ${currentIndex + 1} / ${questions.size}"
        questionText.text = q.text
        nextButton.isEnabled = false
        feedbackText.visibility = TextView.GONE

        // Reset buttons
        hackButton.isEnabled = true
        mythButton.isEnabled = true
        hackButton.setBackgroundColor(Color.parseColor("#E0E0E0"))
        mythButton.setBackgroundColor(Color.parseColor("#E0E0E0"))
        hackButton.setTextColor(Color.BLACK)
        mythButton.setTextColor(Color.BLACK)

        if (currentIndex == questions.size - 1) {
            nextButton.text = "FINISH"
        } else {
            nextButton.text = "NEXT"
        }
    }

    private fun checkAnswer(userSaysHack: Boolean) {
        val q = questions[currentIndex]
        val isCorrect = userSaysHack == q.isHack

        if (isCorrect) score++

        // Disable buttons
        hackButton.isEnabled = false
        mythButton.isEnabled = false
        nextButton.isEnabled = true

        // Highlight correct/wrong
        if (userSaysHack) {
            hackButton.setBackgroundColor(if (isCorrect) Color.parseColor("#4CAF50") else Color.parseColor("#F44336"))
            hackButton.setTextColor(Color.WHITE)
        } else {
            mythButton.setBackgroundColor(if (isCorrect) Color.parseColor("#4CAF50") else Color.parseColor("#F44336"))
            mythButton.setTextColor(Color.WHITE)
        }

        // Show feedback
        feedbackText.visibility =

