package com.example.homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var sugar = "無糖";
        var ice_opt = "去冰";
        var rg1:RadioGroup = findViewById(R.id.radioGroup)
        var rg2:RadioGroup = findViewById(R.id.radioGroup2)
        var send_btn:Button = findViewById(R.id.send_btn)
        var set_drink:EditText = findViewById(R.id.set_drink)
        rg1.setOnCheckedChangeListener { _, i ->
            sugar = when(i){
                R.id.radioButton -> radioButton.text.toString()
                R.id.radioButton2 -> radioButton2.text.toString()
                R.id.radioButton3 -> radioButton3.text.toString()
                R.id.radioButton4 -> radioButton4.text.toString()
                else -> " "
            }
        }
        rg2.setOnCheckedChangeListener { _, i ->
            ice_opt = when(i){
                R.id.radioButton14 -> radioButton14.text.toString()
                R.id.radioButton15 -> radioButton15.text.toString()
                R.id.radioButton16 -> radioButton16.text.toString()
                else -> " "
            }
        }
        send_btn.setOnClickListener{
            var drink:String = set_drink.text.toString()
            var i = Intent()
            var b = Bundle()
            b.putString("sugar",sugar)
            b.putString("drink",drink)
            b.putString("ice",ice_opt)
            i.putExtras(b)
            setResult(101,i)
            finish()
        }
    }
}