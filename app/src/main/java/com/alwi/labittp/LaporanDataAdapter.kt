package com.alwi.labittp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LaporanDataAdapter(
    private val listku: ArrayList<LaporanData>
): RecyclerView.Adapter<LaporanDataAdapter.LaporanViewHolder>(){
    inner class LaporanViewHolder(viewku: View):RecyclerView.ViewHolder(viewku) {
        var tv_namaBarang: TextView = viewku.findViewById(R.id.list_namaBarang)
        var tv_jumlah: TextView = viewku.findViewById(R.id.list_jumlahBarang)
        var tv_tanggalMasuk: TextView = viewku.findViewById(R.id.list_tanggalMasukBarang)
        var tv_deskripsi: TextView = viewku.findViewById(R.id.list_deskripsiBarang)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder{
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_laporan, parent, false)
        return LaporanViewHolder(viewView)

    }

    override fun onBindViewHolder(holder: LaporanViewHolder, position: Int) {
        val dataku = listku[position]
        holder.tv_namaBarang.text = dataku.namaBarang
        holder.tv_jumlah.text = dataku.jumlah
        holder.tv_tanggalMasuk.text = dataku.tanggalMasuk
        holder.tv_deskripsi.text = dataku.deskripsi
    }

    override fun getItemCount(): Int {
        return listku.size
    }

}