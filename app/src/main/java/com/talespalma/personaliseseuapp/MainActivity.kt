package com.talespalma.personaliseseuapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.talespalma.personaliseseuapp.database.DataBaseHelper
import com.talespalma.personaliseseuapp.databinding.ActivityAdicionarListaBinding
import com.talespalma.personaliseseuapp.databinding.ActivityMainBinding
import com.talespalma.personaliseseuapp.model.DataBaseDAO
import com.talespalma.personaliseseuapp.model.Produtos
import com.talespalma.personaliseseuapp.recyclerview.AdapterView

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapterViewHolder = AdapterView()

    override fun onStart() {
        super.onStart()
        inicarLista()
        DataBaseDAO(this).selectQuery()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)





        with(binding){
            recyclerView.adapter = adapterViewHolder
            recyclerView.layoutManager = LinearLayoutManager(baseContext)
            fabAdicionar.setOnClickListener { telaAdd() }
            btnLimpar.setOnClickListener { DataBaseDAO(applicationContext).limpar() }
        }

    }

    private fun inicarLista(){
        adapterViewHolder.inserirLista( DataBaseDAO(this).select())
    }

    private fun telaAdd() {
        startActivity(Intent(this,AdicionarListaActivity::class.java))
    }
}