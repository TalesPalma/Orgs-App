package com.talespalma.personaliseseuapp.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.talespalma.personaliseseuapp.databinding.RecyclerViewItemBinding
import com.talespalma.personaliseseuapp.model.DataBaseDAO
import com.talespalma.personaliseseuapp.model.Produtos

class AdapterView : Adapter<AdapterView.AdapterViewHolder>() {

    private var list: List<Produtos> = listOf()
    private var context: Context? = null

    inner class AdapterViewHolder(
        bindingView: RecyclerViewItemBinding
    ) : ViewHolder(bindingView.root) {
        val binding = bindingView
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
        holder.binding.textViewTitulos.text = list[position].title
        holder.binding.textViewDescription.text = list[position].descricao
        holder.binding.textViewPreco.text = list[position].preco

        holder.binding.btnExcluir.setOnClickListener {
            deletarDado(list[position].id,position)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deletarDado(id: Int,position: Int) {
        DataBaseDAO(context!!).delete(id)
        inserirLista(DataBaseDAO(context!!).select())
        notifyItemRemoved(position)
    }


}