package com.talespalma.personaliseseuapp.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.ImageLoader
import coil.load
import com.talespalma.personaliseseuapp.R
import com.talespalma.personaliseseuapp.databinding.RecyclerViewItemBinding
import com.talespalma.personaliseseuapp.model.DataBaseDAO
import com.talespalma.personaliseseuapp.model.Produtos
import com.talespalma.personaliseseuapp.tools.CoilTools

class AdapterView : Adapter<AdapterView.AdapterViewHolder>() {

    private var list: List<Produtos> = listOf()
    private var context: Context? = null

    inner class AdapterViewHolder(
        bindingView: RecyclerViewItemBinding
    ) : ViewHolder(bindingView.root) {
        val binding = bindingView

        fun setInfos(produtos: Produtos) {
            with(binding){
                textViewTitulos.text = produtos.title
                textViewDescription.text = produtos.descricao
                textViewPreco.text = produtos.preco
                    val imageLoaderGif = CoilTools.imageLoadGif(context!!)
                    imageProduto.load(produtos.uri,imageLoaderGif){
                        fallback(R.drawable.image_not_found)
                        error(R.drawable.image_not_found)
                        placeholder(R.drawable.loading_ps)
                }
            }
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun inserirLista(listaExterna: List<Produtos>) {
        list = listaExterna
        notifyDataSetChanged()
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        context = parent.context
        val view = RecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.setInfos(list[position])
        holder.binding.btnExcluir.setOnClickListener {
            deletarDado(list[position].id, position)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deletarDado(id: Int, position: Int) {
        DataBaseDAO(context!!).delete(id)
        inserirLista(DataBaseDAO(context!!).select())
        notifyItemRemoved(position)
    }


}