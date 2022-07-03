package com.alwi.labittp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaporanActivity : AppCompatActivity() {

    lateinit var rv_inventaris: RecyclerView
    lateinit var apiService: ServiceInterface
    private var ambilData: ArrayList<LaporanData> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        rv_inventaris = findViewById(R.id.list_inventaris)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)

        apiService.getData().enqueue(object : Callback<List<LaporanData>>{
            override fun onResponse(
                call: retrofit2.Call<List<LaporanData>>,
                response: Response<List<LaporanData>>
            ){
                if (response.isSuccessful){
                    val res = response.body()
                    ambilData.addAll(res!!)
                    rv_inventaris.layoutManager = LinearLayoutManager(this@LaporanActivity)
                    rv_inventaris.adapter = LaporanDataAdapter(ambilData)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<LaporanData>>, t: Throwable) {

            }
        })
    }
}