package com.example.kotlin_hw5_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            //kotlin不用加上com.example，但是class後面要加上.java
            startService(intent)
            Toast.makeText(this,"啟動Service",Toast.LENGTH_LONG).show()
            finish()
        }
    }
}