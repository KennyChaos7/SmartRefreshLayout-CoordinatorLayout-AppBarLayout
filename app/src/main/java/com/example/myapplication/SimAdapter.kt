package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SimAdapter: RecyclerView.Adapter<SimpleViewHolder>() {

    private val dataList: MutableList<String> = mutableListOf<String>()

    fun newAdd() {
        dataList.clear()
        add()
    }

    fun add() {
        for (index: Int in 0..20)
            dataList.add("")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.setText(R.id.textview, "$position")
    }

    override fun getItemCount() = dataList.size
}