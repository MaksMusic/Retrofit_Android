package com.music.retrofit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.music.retrofit.adapter.AdapterMessage
import com.music.retrofit.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections.list

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var retrofit: Retrofit
    lateinit var servisesApi: ServisesApi
    private  var adapterMessage:AdapterMessage = AdapterMessage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init
        binding.recycler.adapter = adapterMessage

        //fun  getHttpLoggingInterceptor
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        //fun  getOkHttpClient
        var client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit =
            Retrofit.Builder().baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
        servisesApi = retrofit.create(ServisesApi::class.java)
        var messageCall = servisesApi.message()

        //получение данных
        messageCall.enqueue(object : Callback<List<com.music.retrofit.Message>> {
            override fun onResponse(
                call: Call<List<com.music.retrofit.Message>>,
                response: Response<List<com.music.retrofit.Message>>
            ) {
                if (response.isSuccessful) {
                     response.body()?.let {
                            adapterMessage.addMessList(it)
                               // Log.e("sms", message.id.toString())
                          }
                    }else{
                        Log.e("sms", response.errorBody().toString())
                    }
            }

            //exeption
            override fun onFailure(call: Call<List<com.music.retrofit.Message>>, t: Throwable) {
                Log.e("sms", "error ")
            }
        })


    }
}