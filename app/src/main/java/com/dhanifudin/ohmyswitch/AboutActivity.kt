package com.dhanifudin.ohmyswitch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupRepositoryLink()
    }

    fun setupRepositoryLink() {
        val repositoryText: TextView = findViewById(R.id.repository_text)
        repositoryText.movementMethod = LinkMovementMethod.getInstance()
    }
}