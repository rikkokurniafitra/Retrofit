package com.rikko.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list =ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPosts().enqueue(object: Callback<ArrayList<PostResponse>>{
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response:
                Response<ArrayList<PostResponse>>
            ) {
                val resposeCode = response.code().toString()
                tvResponse.text = resposeCode
                response.body()?.let { list.addAll(it)}
                val adapter = PostAdapter(list)
                rvPost.adapter = adapter
            }
            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
            }

        })
    }
}



