package com.ubaya.ubayakost160419147.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayakost160419147.model.Kost

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val kostDetailLD = MutableLiveData<Kost>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(nama:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/cnathannael09/bd227b38112343d9720bb7d08a21ed54/raw/3e0b9aae13ea0adc01494376413de2200d4c3173/newKost.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Kost>>() { }.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                for(item in result){
                    if (nama == item.nama){
                        kostDetailLD.value = item
                    }
                }
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
}