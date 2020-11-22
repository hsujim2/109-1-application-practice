package com.example.kotlin_hw4_1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士")
        val transPhotoIdArray = arrayOf(R.drawable.trans1,R.drawable.trans2,R.drawable.trans3,R.drawable.trans4)

        var transData = Array(transNameArray.size){Data()}

        for(i in transData.indices){
            transData[i].add(transNameArray[i],transPhotoIdArray[i])
        }
        val transAdapter = MyAdapter(this,transData,R.layout.trans_list)
        val spinner:Spinner = findViewById(R.id.spinner)
        spinner.adapter = transAdapter

        val messageArray = arrayOf("訊息1","訊息2","訊息3","訊息4","訊息5","訊息6")
        val messageAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,messageArray)

        val listView:ListView = findViewById(R.id.listView)
        listView.adapter = messageAdapter

        val cubeNameArray = arrayOf("哭哭","發抖","再見","生氣","昏倒","竊笑","很棒","你好","驚嚇","大笑")
        val cubePhotoIdArray = arrayOf(R.drawable.cubee1,R.drawable.cubee2,R.drawable.cubee3,R.drawable.cubee4,
                R.drawable.cubee5,R.drawable.cubee6,R.drawable.cubee7,R.drawable.cubee8,R.drawable.cubee9,R.drawable.cubee10)
        var cubeData= Array(cubeNameArray.size){Data()}
        for(i in cubeData.indices){
            cubeData[i].add( cubeNameArray[i],cubePhotoIdArray[i])
        }

        val cubeAdapter = MyAdapter(this,cubeData,R.layout.cubee_list)

        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = cubeAdapter
        gridView.numColumns = 3

    }

    class Data  {
        lateinit var name: String
        var photo: Int =0
        fun add(a:String,b:Int){
            this.name = a
            this.photo = b
        }
    }

    class MyAdapter(context :Context,data: Array<Data>, view: Int) : BaseAdapter() {
        var context = context
        var data = data
        var view = view
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            convertView = LayoutInflater.from(context).inflate(view,parent,false)
            val name = convertView.findViewById<TextView>(R.id.name)
            name.text = data[position].name
            val imageview = convertView.findViewById<ImageView>(R.id.imageView)
            imageview.setImageResource(data[position].photo)
            return convertView
        }
        override fun getItem(position: Int): Any {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return data.size
        }
    }
}