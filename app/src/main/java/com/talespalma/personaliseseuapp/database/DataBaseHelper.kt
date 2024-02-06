package com.talespalma.personaliseseuapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(
    val context: Context
) : SQLiteOpenHelper(
    context, "ListaCompras", null, 1
) {

    companion object{
        const val TABLE_NAME = "produtos"
        const val ID = "id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val PRECO = "PRECO"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val sql:String = "CREATE TABLE if not EXISTS $TABLE_NAME(\n" +
                    "  $ID INTEGER not NULL PRIMARY KEY  AUTOINCREMENT \n" +
                    "  ,$TITLE varchar(50)\n" +
                    "  ,$DESCRIPTION text " +
                    ",$PRECO varchar(50) )"
            db?.execSQL(sql)
            Log.i("info_database","Data base create !!!")
        }catch (e :Exception){
            Log.i("info_database","Erro ao criar bd ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("info_database","upgrade database")
    }
}