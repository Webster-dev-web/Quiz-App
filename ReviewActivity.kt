package com.example.quizapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.LinearLayout

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val container = findViewById<LinearLayout>(R.id.reviewContainer)

        val sample = listOf(
            "Drinking coffee helps you sleep better → Myth",
            "Turning off notifications improves focus → Hack",
            "Cracking knuckles causes arthritis → Myth"
        )

        for (item in sample) {
            val tv = TextView(this)
            tv.text = item
            tv.textSize = 16f
            tv.setPadding(0, 10, 0, 10)
            container.addView(tv)
        }
    }
}