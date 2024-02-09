package com.talespalma.personaliseseuapp.recyclerview.ui

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import coil.load
import com.talespalma.personaliseseuapp.R
import com.talespalma.personaliseseuapp.databinding.ActivityAdicionarListaBinding
import com.talespalma.personaliseseuapp.databinding.FormularioImagemBinding
import com.talespalma.personaliseseuapp.model.DataBaseDAO
import com.talespalma.personaliseseuapp.tools.CoilTools
import java.text.NumberFormat
import java.util.Locale

class AlertDialogs(
    val context: Context,
    val layoutInflater: LayoutInflater
) {
    val mainBinding by lazy {
        ActivityAdicionarListaBinding.inflate(layoutInflater)
    }
    private var uri: String = ""
    fun inserirDataBase(title: String, descriptor: String, preco: String) {
        val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        val precoFormatado = formatador.format(preco.toInt())
        DataBaseDAO(context).inserir(title, descriptor, precoFormatado, uri)
    }

    fun BuiderAlerDialogAdd(finalFuncao: (String) -> Unit) {
        val bindingFormularioImage = FormularioImagemBinding.inflate(layoutInflater)
        val imageLoader = CoilTools.imageLoadGif(context)

        val urlBd = DataBaseDAO(context).selectQuery()

        urlBd?.let {
            bindingFormularioImage.formularioEditTextUsuario.setText(it)
            bindingFormularioImage.formularioImageViewUsuario.load(it, imageLoader) {
                fallback(R.drawable.image_not_found)
                error(R.drawable.image_not_found)
                placeholder(R.drawable.loading_ps)
            }
        }

        bindingFormularioImage.formulariButtonCarregar.setOnClickListener {
            val dataBaseDAO = DataBaseDAO(context)
            uri = bindingFormularioImage.formularioEditTextUsuario.text.toString()
            bindingFormularioImage.formularioImageViewUsuario.load(uri, imageLoader) {
                fallback(R.drawable.image_not_found)
                error(R.drawable.image_not_found)
                placeholder(R.drawable.loading_ps)
            }
            finalFuncao(uri)
        }
        AlertDialog.Builder(context).setView(bindingFormularioImage.root)
            .setPositiveButton("Confirmar") { _, _ -> }.setNegativeButton("Cancelar") { _, _ -> }
            .show()
    }
}


