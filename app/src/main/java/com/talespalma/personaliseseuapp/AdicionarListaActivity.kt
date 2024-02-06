package com.talespalma.personaliseseuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import com.talespalma.personaliseseuapp.R
import com.talespalma.personaliseseuapp.databinding.ActivityAdicionarListaBinding
import com.talespalma.personaliseseuapp.databinding.ActivityMainBinding
import com.talespalma.personaliseseuapp.model.DataBaseDAO

class AdicionarListaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdicionarListaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        with(binding){
            btnAdicionar.setOnClickListener {
                inserirDataBase(inputTitle.text.toString(),inputDescription.text.toString())
                finish()
            }
        }

    }

    private fun inserirDataBase(title:String,descriptor: String) {
        DataBaseDAO(this).inserir(title,descriptor)
    }
}