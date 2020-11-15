package com.example.kotlin_hw3

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.custom_toast.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn:Button = findViewById(R.id.button)
        //setOnClickListener與java有差別
        btn.setOnClickListener(){
            var dialog = AlertDialog.Builder(this)//因為kotlin宣告方式不同，因此這邊也跟著改變，不需要宣告new了
            dialog.setTitle("請選擇功能")//與java相同
            dialog.setMessage("請依據下方按鈕選擇要顯示的物件")

            //////////參考java的方式，但是無法運作////////////
//            dialog.setNeutralButton("取消",DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
//                fun onClick(dialog: DialogInterface?, which: Int) {
//                    Toast.makeText(this@MainActivity,"dialog關閉",Toast.LENGTH_SHORT).show()
//                }
//            })
//            dialog.setNegativeButton("自定義Toast",DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
//                fun onClick(dialog: DialogInterface?, which: Int) {
//                    showToast()
//                }
//            })
//            dialog.setPositiveButton("顯示list",DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
//                fun onClick(dialog: DialogInterface?, which: Int) {
//                    showListDialog()
//                }
//            })
            ///////////////網路上查到的方法，將listener另外新增，在加到setButton後面
            val listener = object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity,"dialog關閉",Toast.LENGTH_SHORT).show()
                }
            }
            //先宣告
            dialog.setNeutralButton("取消",listener)
            val listener2 = object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    showToast()
                }
            }
            dialog.setNegativeButton("自定義Toast",listener2)//把宣告的listener2放到button內
            //第三個按鈕有同理
            val listener3 = object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    showListDialog()
                }
            }
            dialog.setPositiveButton("顯示list",listener3)

            dialog.show()
        }
    }

    fun showToast(){
        var toast = Toast(this)//與java宣告方式不同
        toast.setGravity(Gravity.TOP,0,50)
        toast.duration = Toast.LENGTH_SHORT
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast,findViewById(R.id.custom_toast_root))
        toast.view = layout//setView改成view
        // 雖然寫完程式後view被劃掉，也因此找很久，但是實際編譯會過，功能也正常
        toast.show()
    }

    fun showListDialog(){
        val list = listOf("message1","message2","message3","message4","meaasge5","message6")
        //kotlin宣告陣列的方式不同
        val dialog_list = AlertDialog.Builder(this)
        dialog_list.setTitle("使用LIST呈現")

        AlertDialog.Builder(this).setItems(list.toTypedArray()){_,which ->
            val  name = list[which]
            Toast.makeText(applicationContext,name,Toast.LENGTH_SHORT)
        }.show()
        //kotlin沒有setItems，要使用Builder來創建，其他部分與java都相同
    }
}