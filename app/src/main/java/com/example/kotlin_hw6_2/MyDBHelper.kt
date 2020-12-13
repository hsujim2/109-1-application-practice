package com.example.kotlin_hw6_2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class MyDBHelper(context: Context?) : SQLiteOpenHelper(context, "mdatabase.db", null, 1) {
    //這邊kotlin宣告的方法與java有差別，預設的宣告方式要求MyBDHelper需要四個輸入，不能像java那樣宣告在下面，所以我乾脆把數值填進去
    //後面的程式都與java一樣
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE myTable(book text PRIMARY KEY, price integer NOT NULL)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS myTable")
        onCreate(db)
    }

}