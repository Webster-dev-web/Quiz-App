package com.example.quizapp

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        findViewById<TextView>(R.id.scoreText).text = "You scored $score / $total"

        findViewById<Button>(R.id.restartBtn).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<Button>(R.id.reviewBtn).setOnClickListener {
            startActivity(Intent(this, ReviewActivity::class.java))
        }
    }
}