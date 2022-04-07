package com.ubaya.ubayakost160419147.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakost160419147.R
import com.ubaya.ubayakost160419147.model.Kost
import com.ubaya.ubayakost160419147.util.loadImage
import kotlinx.android.synthetic.main.kost_list_item.view.*

class KostListAdapter(val kostList:ArrayList<Kost>): RecyclerView.Adapter<KostListAdapter.KostViewHolder>() {
    class KostViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
        return KostViewHolder(view)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        var nama = kostList[position].nama
        holder.view.txtNamaKos.text = kostList[position].nama
        holder.view.txtGender.text = kostList[position].gender
        holder.view.txtHarga.text = kostList[position].harga

        holder.view.btnDetail.setOnClickListener{
            val action = KostListFragmentDirections.actionKostDetail(nama.toString())
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imgKostPhoto.loadImage(kostList[position].photoURL, holder.view.progressLoadingKostPhoto)
    }

    override fun getItemCount() = kostList.size

    fun updateKostList(newKostList: ArrayList<Kost>){
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}