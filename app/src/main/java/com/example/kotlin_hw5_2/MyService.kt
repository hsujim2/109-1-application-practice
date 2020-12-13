package com.example.kotlin_hw5_2

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    companion object{var flag = false }//宣告companion object才能讓別的檔案讀取
    var h=0
    var m=0
    var s=0
    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")//這邊只有function宣告與java不同
    }
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        flag = intent.getBooleanExtra("flag",false)
        Thread{//thread不用new，java要，另外，while這段完全與java相同，只差在後面intent跟bundle沒有new
            while (flag){
                try { Thread.sleep(1000) }catch (e:InterruptedException){ e.printStackTrace() }
                s++
                if(s>=60){
                    s=0
                    m++
                    if(m>=60){
                        m=0
                        h++
                    }
                }
                val intent = Intent("MyMessage")//不用new
                val bundle = Bundle()//不用new
                bundle.putInt("H",h)
                bundle.putInt("M",m)
                bundle.putInt("S",s)
                intent.putExtras(bundle)
                sendBroadcast(intent)
            }
        }.start()
        return Service.START_STICKY
    }
}