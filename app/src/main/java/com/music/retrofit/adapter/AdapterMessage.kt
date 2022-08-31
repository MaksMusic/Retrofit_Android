package com.music.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.retrofit.Message
import com.music.retrofit.databinding.ItemBinding
import com.squareup.picasso.Picasso

class AdapterMessage () : RecyclerView.Adapter<AdapterMessage.ViewHolder>(){

    var list = ArrayList<Message>()

    fun addMess(message: Message){
        list.add(message)
        notifyDataSetChanged()
    }

    fun addMessList(list2:List<Message>){
        list.addAll(list2)
        notifyDataSetChanged()
    }
    inner class ViewHolder(var binding: ItemBinding):RecyclerView.ViewHolder(binding.root){
       fun addMessage(message: Message){
           binding.text.text = message.text
           binding.id.text = message.id.toString()
           Picasso.get().load(message.image).into(binding.image)


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