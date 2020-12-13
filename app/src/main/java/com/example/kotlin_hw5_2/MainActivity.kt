package com.example.kotlin_hw5_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //變數宣告方式與java不同
    lateinit var tv_clock:TextView
    lateinit var btn_strat:Button
    var flag:Boolean = false
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {//宣告object底下的BroadcastReceiver，這部分與java不同
        override fun onReceive(context: Context?, intent: Intent) {
            val b = intent.extras
            if (b != null) {//kotlin要處裡null問題
                tv_clock.text = String.format("%02d:%02d:%02d", b.getInt("H"), b.getInt("M"), b.getInt("S")
                )
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_clock = findViewById(R.id.tv_clock)
        btn_strat = findViewById(R.id.btn_start)
        registerReceiver(receiver, IntentFilter("MyMessage"))
        flag = MyService.flag
        if(flag){
            btn_strat.text = "pulse"//java的setText -> text，不過setText其實也能用
        }
        else//除了.text之外都與java相同
            btn_strat.text = "start"
        btn_strat.setOnClickListener {
            flag = !flag
            if(flag){
                btn_strat.text = "pulse"
                Toast.makeText(this,"start timing",Toast.LENGTH_SHORT).show()
            }
            else{
                btn_strat.text = "start"
                Toast.makeText(this,"timing pulse",Toast.LENGTH_SHORT).show()
            }
            startService(Intent(this@MainActivity, MyService::class.java).putExtra("flag", flag))
        }
    }
    override fun onDestroy() { //與java相同
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}