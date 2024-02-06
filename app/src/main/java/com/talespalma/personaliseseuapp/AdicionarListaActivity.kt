package com.talespalma.personaliseseuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import com.talespalma.personaliseseuapp.R
import com.talespalma.personaliseseuapp.databinding.ActivityAdicionarListaBinding
import com.talespalma.personaliseseuapp.databinding.ActivityMainBinding
import com.talespalma.personaliseseuapp.model.DataBaseDAO
import java.text.NumberFormat
import java.util.Locale

class AdicionarListaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdicionarListaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        with(binding){
            btnAdicionar.setOnClickListener {
                inserirDataBase(
                    inputTitle.text.toString()
                    ,inputDescription.text.toString(),
                    editTextPreco.text.toString()
                )
                finish()
            }
        }

    }

    private fun inserirDataBase(title:String,descriptor: String,preco:String) {
        val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        val precoFormatado = formatador.format(preco.toInt())
        DataBaseDAO(this).inserir(title,descriptor,precoFormatado)
    }
}