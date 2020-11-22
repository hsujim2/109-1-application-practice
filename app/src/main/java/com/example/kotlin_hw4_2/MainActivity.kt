package com.example.kotlin_hw4_2

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var rabprogress:Int = 0
    private var turprogress:Int = 0
    lateinit var seekBar:SeekBar
    lateinit var seekBar2:SeekBar
    lateinit var btn_start:Button
    val scope = CoroutineScope(Dispatchers.Default)//用於coroutine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById(R.id.seekBar)
        seekBar2 = findViewById(R.id.seekBar2)
        btn_start = findViewById(R.id.btn_start)
        btn_start.setOnClickListener {
            btn_start.isEnabled = false
            //初始化變數
            rabprogress = 0
            turprogress = 0
            seekBar.setProgress(0)
            seekBar2.setProgress(0)
            runThread()//呼叫副程式
        }
    }
    //因為在coroutine上沒發現handler，所以我一律用io thread來寫兔子以及烏龜的進度
    //我一樣將程式寫在副程式中，但是我只用一個副程式
    private fun runThread(){
        //相較於舊的trhead方法，coroutin則是用launch，其中scope是我在前面新增的變數，是CoroutineScope類別的
        scope.launch() {
            withContext(Dispatchers.IO){//Dispatchers.IO的目的是讓他處於IO thread，相當於舊的runinbackground
                while(turprogress <= 100 && rabprogress < 100){
                    try{
                        Thread.sleep(100)//sleep這部分的程式碼沒有差別
                        turprogress += (0..3).random()//kotlin特別的用法，(0..3)是0~3的變數，而且這樣寫會自動轉成int
                        //publishProgress(turprogress);
                        //原本使用publishprogress去廣播我現在的進度，由handler來接收，因此當這項有改變時，handler便會啟動
                        //我現在則是使用withContext，.Main代表的是main thread，我這邊用來更新畫面
                        //當負載比較重時，main thread不會受影響，優先權比較高，可以保持畫面的流暢
                        withContext(Dispatchers.Main){
                            seekBar2.progress = turprogress//更新進度
                            if (turprogress >= 100 && rabprogress < 100) {//判斷是否勝利
                                Toast.makeText(this@MainActivity, "烏龜勝利", Toast.LENGTH_SHORT).show()
                                btn_start.isEnabled = true//讓按鈕恢復，可以再次被按下
                            }
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
        scope.launch (){//第二個thread，用於計算兔子的進度，與烏龜的程式幾乎相同
            withContext(Dispatchers.IO){//一樣使用背景執行的IO thread來做累加以及延遲
                while (rabprogress <= 100 && turprogress < 100) {
                    try {
                        Thread.sleep(100)
                        rabprogress += (0..3).random()

                        withContext(Dispatchers.Main){//一樣將更新的程式寫在main thread中
                            seekBar.progress = rabprogress
                            if (rabprogress >= 100 && turprogress < 100) {
                                Toast.makeText(this@MainActivity, "兔子勝利", Toast.LENGTH_SHORT).show()
                                btn_start.isEnabled = true
                            }
                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
//原本嘗試使用suspend，但是程式無法過編譯，我也無法解決，所以後來沒有用suspend，使用launch來執行程式
//    override fun onResume() {
//        super.onResume()
//        btn_start.setOnClickListener{
//            btn_start.isEnabled = false
//
//            var rabprogress = 0
//            var turprogress = 0
//            seekBar.setProgress(0)
//            seekBar2.setProgress(0)
//            scope.launch {
//                myTask()
//            }
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        scope.coroutineContext.cancel()
//    }
//    private suspend fun myTask(){
//        try{
//            Thread.sleep(100)
//            turprogress += (0..3).random()
//            //publishProgress(turprogress);
//            seekBar2.setProgress(turprogress)
//
//            withContext(Dispatchers.Main){
//                if (turprogress >= 100 && rabprogress < 100) {
//                    Toast.makeText(this@MainActivity, "烏龜勝利", Toast.LENGTH_SHORT).show()
//                    btn_start.isEnabled = true
//                }
//            }
//        }catch (e:Exception){
//        }
//    }
}