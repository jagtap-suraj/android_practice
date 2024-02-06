package com.example.deeznutz

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofit.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val dataList = response.body()?.data
                val helloText = findViewById<TextView>(R.id.helloText)
                helloText.text = dataList.toString()
                Log.d("MainActivity", "onResponse: $dataList")
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ${t.message}")
            }
        })
    }


}