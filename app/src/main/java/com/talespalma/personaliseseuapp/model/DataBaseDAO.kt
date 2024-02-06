package com.talespalma.personaliseseuapp.model

import android.content.Context
import android.util.Log
import androidx.core.content.contentValuesOf
import com.talespalma.personaliseseuapp.database.DataBaseHelper

class DataBaseDAO(val context: Context) : IDataBaseDAO {

    private val writer = DataBaseHelper(context).writableDatabase
    private val reader = DataBaseHelper(context).readableDatabase


    override fun inserir(title: String, description: String): Boolean {
        val content = contentValuesOf(
            DataBaseHelper.TITLE to title,
            DataBaseHelper.DESCRIPTION to description
        )
        try {
            writer.insert(DataBaseHelper.TABLE_NAME, null, content)
            Log.i("info_database", "Dados inserido com sucesso")
        } catch (e: Exception) {
            Log.i("info_database", "Erro ao criar bd ${e.message}")
            return false
        }
        return true
    }

    override fun delete(id: Int): Boolean {
        try {
            val args = arrayOf(id.toString())
            writer.delete(DataBaseHelper.TABLE_NAME, "id = ?", args)
            Log.i("info_database", "Deletado com sucesso")
        } catch (e: Exception) {
            Log.i("info_database", "erro com delete : ${e.message}")
            return false
        }
        return true
    }

    override fun select(): List<Produtos> {
        val list = mutableListOf<Produtos>()
        try {
            val sql = "SELECT * FROM ${DataBaseHelper.TABLE_NAME}"
            val query = reader.rawQuery(sql, null)

            while (query.moveToNext()) {
                val id = query.getColumnIndex(DataBaseHelper.ID)
                val title = query.getColumnIndex(DataBaseHelper.TITLE)
                val description = query.getColumnIndex(DataBaseHelper.DESCRIPTION)
                list.add(
                    Produtos(
                        query.getInt(id),
                        query.getString(title),
                        query.getString(description)
                    )
                )
            }
            Log.i("info_database", "selecionado com sucesso")
        } catch (e: Exception) {
            Log.i("info_database", "erro selecionar ${e.message}")
        }
        return list.toList()
    }
}