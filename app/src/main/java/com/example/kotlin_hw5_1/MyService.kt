package com.example.kotlin_hw5_1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.lang.InterruptedException as InterruptedException1

class MyService : Service() {//kotlin的extends是用冒號
    override fun onCreate() {
        super.onCreate()
        Thread{//kotlin thread不用new
            try {
                Thread.sleep(5000)
                val intent = Intent(this, MainActivity2::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK//set拿掉，改用flags=
                startActivity(intent)
            }catch (e: InterruptedException1){
                e.printStackTrace()
            }
        }.start()
        stopSelf()
    }
//下面除了宣告方式以外都一樣
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }
    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented");
    }
}