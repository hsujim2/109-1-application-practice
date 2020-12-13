package com.example.kotlin_hw6_2

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    //////宣告，kotlin不同於java，用lateinit可以不指定初值/////
    lateinit var ed_book:EditText
    lateinit var ed_price:EditText
    lateinit var btn_query:Button
    lateinit var btn_insert:Button
    lateinit var btn_update:Button
    lateinit var btn_delete:Button
    lateinit var listView:ListView
    lateinit var adapter:ArrayAdapter<String>
    var items = ArrayList<String>()//這行如果用上面的lateinit，會因為沒有宣告，在宣告arraylist時出錯
    lateinit var dbrw:SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed_book = findViewById(R.id.ed_book)
        ed_price = findViewById(R.id.ed_price)
        btn_query = findViewById(R.id.btn_query)
        btn_insert = findViewById(R.id.btn_insert)
        btn_update = findViewById(R.id.btn_update)
        btn_delete = findViewById(R.id.btn_delete)
        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)//kotlin不用new
        listView.adapter = adapter
        dbrw = MyDBHelper(this).writableDatabase//kotlin上get拿掉了
        btn_query.setOnClickListener {
            val c:Cursor
            if(ed_book.length() <1)
                c = dbrw.rawQuery("SELECT * FROM myTable", null)//kotlin 寫SQL語法與java相同
            else
                c = dbrw.rawQuery("SELECT * FROM myTable WHERE book LIKE '" + ed_book.text + "'", null)
            c.moveToFirst()//這邊的語法也一樣
            items.clear()
            Toast.makeText(this, "共有" + c.count + "筆資料", Toast.LENGTH_SHORT).show()//makeText語法也是一樣
            var i=0
            for (i in 0 until c.count) {//kotlin for迴圈寫法不一樣，0是初值，until後面擺結束值
                items.add("書名" + c.getString(0) + "\t\t\t\t價格:" + c.getString(1))//一樣
                c.moveToNext()
            }
            adapter.notifyDataSetInvalidated()
            c.close()
        }
        btn_insert.setOnClickListener {
            if(ed_book.length()<1 || ed_price.length()<1)
                Toast.makeText(this, "欄位請勿留空", Toast.LENGTH_SHORT).show()
            else{
                try {
                    dbrw.execSQL("INSERT INTO myTable(book,price)VALUES(?,?)", arrayOf<Any>(ed_book.text.toString(), ed_price.text))
                    //kotlin這邊我改用arrayof我不清楚原來的object能不能用，但是這樣比較方便
                    Toast.makeText(this,"新增書名"+ed_book.text+"    價格"+ed_price.text,Toast.LENGTH_SHORT).show()//getText可以改成.text，也不用加上toString
                    ed_book.setText("")//這邊的一定要用setText，因為.text只能指定變數給他，不能指定字串
                    ed_price.setText("")
                }catch (e:Exception){
                    Toast.makeText(this,"新增失敗"+e.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
        btn_update.setOnClickListener {//與上面的btn雷同
            if(ed_book.length()<1 || ed_price.length()<1)
                Toast.makeText(this, "欄位請勿留空", Toast.LENGTH_SHORT).show()
            else{
                try {
                    dbrw.execSQL("UPDATE myTable SET price = "+ed_price.text+" WHERE book LIKE '"+ed_book.text+"'")
                    Toast.makeText(this,"更新書名"+ed_book.text+"    價格"+ed_price.text,Toast.LENGTH_SHORT).show()
                    ed_book.setText("")
                    ed_price.setText("")
                }catch (e:Exception){
                    Toast.makeText(this,"更新失敗"+e.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
        btn_delete.setOnClickListener {//也與上面的btn雷同
            if(ed_book.length()<1){
                Toast.makeText(this, "書名請勿留空", Toast.LENGTH_SHORT).show()
            }
            else{
                try {
                    dbrw.execSQL("DELETE FROM myTable WHERE book LIKE '"+ed_book.text+"'")
                    Toast.makeText(this,"刪除書名"+ed_book.text,Toast.LENGTH_SHORT).show()
                    ed_book.setText("")
                    ed_price.setText("")
                }catch (e:Exception){
                    Toast.makeText(this,"刪除失敗"+e.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        dbrw.close()
    }
}