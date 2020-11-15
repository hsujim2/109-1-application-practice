package com.example.homework2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //java寫法
    //在kotlin上會因為事先宣告了null，導致後面無法存取變數，看網路上的文章是說kotlin的安全性太嚴格導致的
//    private var ed_name: EditText? = null
//    private var tv_text: TextView? = null
//    private var tv_name:TextView? = null
//    private var tv_winner:TextView? = null
//    private var tv_mmora:TextView? = null
//    private var tv_cmora:TextView? = null
//    private var btn_scissor:RadioButton? = null
//    private var btn_stone:RadioButton? = null
//    private var btn_paper:RadioButton? = null
//    private var btn_mora:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //變數宣告時同時給值就可以避免變數宣告要給null的問題
        //相較於java，kotlin在宣告時變數型態是接在變數名稱後面，而findViewById這邊則沒有改變
        var ed_name:EditText = findViewById(R.id.ed_name)
        var tv_text:TextView = findViewById(R.id.tv_text)
        var tv_name:TextView = findViewById(R.id.tv_name)
        var tv_winner:TextView = findViewById(R.id.tv_winner)
        var tv_mmora:TextView = findViewById(R.id.tv_mmora)
        var tv_cmora:TextView = findViewById(R.id.tv_cmora)
        var btn_scissor:RadioButton = findViewById(R.id.btn_scissor)
        var btn_stone:RadioButton = findViewById(R.id.btn_stone)
        var btn_paper:RadioButton = findViewById(R.id.btn_paper)
        var btn_mora:Button = findViewById(R.id.btn_mora)
        //kotlin的onClickListener宣告比java簡單可以省去new那段，直接進入程式
        btn_mora.setOnClickListener() {
            if (ed_name.length() < 1)//kotlin需要一段時間讓他同步變數的樣子，我一開始打這行變數名稱沒有底線，也會顯示紅字，但是重打幾次就好了
                tv_text.text = "請輸入玩家姓名"//text部分變得很簡單，變成很直觀的.text然後用等號
            else {
                tv_name.text =String.format("名字\n%s", ed_name.text.toString())//getText也變成text，但是一樣要加上toString
//                if(btn_scissor.isChecked)
//                    tv_mmora.text = "我方出拳\n剪刀"
//                else if(btn_stone.isChecked)
//                    tv_mmora.text = "我方出拳\n石頭"
//                else if(btn_paper.isChecked)
//                    tv_mmora.text = "我方出拳\n布"
                when (btn_scissor.isChecked){
                    //kotlin很龜毛，他規定不能使用cascode if，因此在需要改成when，改成when之後變得超級麻煩，對於不同變數的else if反而變得複雜
                    true -> tv_mmora.text = "我方出拳\n剪刀"
                    false -> when (btn_stone.isChecked){
                        true -> tv_mmora.text = "我方出拳\n石頭"
                        false -> when (btn_paper.isChecked){
                            true ->tv_mmora.text = "我方出拳\n布"
                        }
                    }
                }

                var computer:Int = (Math.random()*3).toInt() //轉成int的方式也改變了，我覺得java比較直觀
//                if(computer == 0)
//                    tv_mmora.text = "我方出拳\n剪刀"
//                else if(computer == 1)
//                    tv_mmora.text = "我方出拳\n石頭"
//                else if(computer == 2)
//                    tv_mmora.text = "我方出拳\n布"
                when (computer){//這邊改成when反而比較簡單，有點類似case的概念
                    0 -> tv_cmora.text = "電腦出拳\n剪刀"
                    1 -> tv_cmora.text = "電腦出拳\n石頭"
                    2 -> tv_cmora.text = "電腦出拳\n布"
                }
                //不知為何這邊的if就沒事，可以正常使用，真是詭異
                //這邊的程式與java沒太大區別，isChecked少了括號以及前面提到的.text
                if((btn_scissor.isChecked && computer == 2) || (btn_stone.isChecked && computer == 0) || (btn_paper.isChecked && computer == 1)){
                    tv_winner.text = "勝利者\n" + ed_name.text.toString()
                    tv_text.text = "恭喜你獲勝了!!!"
                }
                else if((btn_scissor.isChecked && computer == 1) || (btn_stone.isChecked && computer == 2) || (btn_paper.isChecked && computer == 0)){
                    tv_winner.text = "勝利者\n電腦"
                    tv_text.text = "可惜，電腦獲勝了！"
                }
                else{
                    tv_winner.text = "勝利者\n平手"
                    tv_text.text = "平局，請再試一次！"
                }
            }

        }
    }
}