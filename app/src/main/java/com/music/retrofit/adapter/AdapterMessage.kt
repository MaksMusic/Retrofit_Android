package com.music.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.retrofit.Message
import com.music.retrofit.databinding.ItemBinding

class AdapterMessage () : RecyclerView.Adapter<AdapterMessage.ViewHolder>(){

    var list = ArrayList<Message>()

    fun addMess(message: Message){
        list.add(message)
    }
    inner class ViewHolder(var binding: ItemBinding):RecyclerView.ViewHolder(binding.root){
       fun addMessage(message: Message){
           list.add(message)
           notifyDataSetChanged()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.addMessage(list[position])
    }

    override fun getItemCount(): Int {
      return  list.size
    }

}