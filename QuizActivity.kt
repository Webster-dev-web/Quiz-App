package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Question(
    val text: String,
    val isTrue: Boolean,
    val explanation: String
)

class QuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var score = 0
    private val userAnswers = mutableListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questions = listOf(
            Question("Drinking coffee helps you sleep better.", false, "Caffeine keeps you awake."),
            Question("Turning off notifications improves focus.", true, "Less distraction."),
            Question("Cracking knuckles causes arthritis.", false, "No scientific proof."),
            Question("Writing tasks down improves memory.", true, "Helps brain recall."),
            Question("Cold showers boost alertness.", true, "Stimulates circulation.")
        )

        loadQuestion()

        findViewById<Button>(R.id.btnTrue).setOnClickListener { checkAnswer(true) }
        findViewById<Button>(R.id.btnFalse).setOnClickListener { checkAnswer(false) }
        findViewById<Button>(R.id.nextBtn).setOnClickListener { nextQuestion() }
    }

    private fun loadQuestion() {
        val q = questions[currentIndex]

        findViewById<TextView>(R.id.questionText).text = q.text
        findViewById<TextView>(R.id.questionCounter).text =
            "Question ${currentIndex + 1}/${questions.size}"

        findViewById<ProgressBar>(R.id.progressBar).max = questions.size
        findViewById<ProgressBar>(R.id.progressBar).progress = currentIndex + 1

        findViewById<TextView>(R.id.feedbackText).visibility = View.GONE
        findViewById<Button>(R.id.nextBtn).isEnabled = false
    }

    private fun checkAnswer(answer: Boolean) {
        val correct = questions[currentIndex].isTrue
        userAnswers.add(answer)

        val feedback = findViewById<TextView>(R.id.feedbackText)
        feedback.visibility = View.VISIBLE

        if (answer == correct) {
            score++
            feedback.text = "Correct!\n${questions[currentIndex].explanation}"
        } else {
            feedback.text = "Wrong!\n${questions[currentIndex].explanation}"
        }

        findViewById<Button>(R.id.nextBtn).isEnabled = true
    }

    private fun nextQuestion() {
        currentIndex++

        if (currentIndex < questions.size) {
            loadQuestion()
        } else {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("total", questions.size)
            intent.putExtra("answers", userAnswers.toBooleanArray())
            startActivity(intent)
            finish()
        }
    }
}