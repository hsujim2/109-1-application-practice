package com.example.homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var tv_meal: TextView = findViewById(R.id.tv_meal)
        if(requestCode == 1){
            if(resultCode == 101 && data != null){
                data.apply {
                    var str1 = getStringExtra("drink")
                    var str2 = getStringExtra("sugar")
                    var str3 = getStringExtra("ice")
                    tv_meal.text = String.format("飲料: %s\n\n甜度: %s\n\n冰塊: %s\n\n",str1,str2,str3)
                }

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn: Button = findViewById(R.id.btn_choice)
        btn.setOnClickListener(){
            var intent = Intent(this,MainActivity2::class.java)
            startActivityForResult(intent,1)
        }
    }
}