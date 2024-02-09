package com.talespalma.personaliseseuapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.talespalma.personaliseseuapp.databinding.ActivityAdicionarListaBinding
import com.talespalma.personaliseseuapp.recyclerview.ui.AlertDialogs
import com.talespalma.personaliseseuapp.tools.CoilTools

class AdicionarListaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarListaBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val alert = AlertDialogs(this, layoutInflater)

        binding.btnAdicionar.setOnClickListener {
            alert.inserirDataBase(
                binding.inputTitle.text.toString(),
                binding.inputDescription.text.toString(),
                binding.editTextPreco.text.toString()
            )
            finish()
        }

        binding.imageAdd.setOnClickListener {
            val imageLoader = CoilTools.imageLoadGif(this)
            alert.BuiderAlerDialogAdd(){
                binding.imageAdd.load(it,imageLoader)
            }

            binding.textIndications.visibility = View.INVISIBLE
        }

    }
}