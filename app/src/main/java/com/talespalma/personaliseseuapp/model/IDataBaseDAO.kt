package com.talespalma.personaliseseuapp.model

interface IDataBaseDAO {
    fun inserir(title: String, description: String): Boolean
    fun delete(id: Int): Boolean
    fun select(): List<Produtos>
}

