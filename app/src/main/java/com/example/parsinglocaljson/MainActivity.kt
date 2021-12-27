package com.example.parsinglocaljson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var rvAdapter: RVAdapter
    private lateinit var recyclerView : RecyclerView
    private var list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(list)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        try {
            val jsonString = assets.open("data.json").bufferedReader().use { it.readText() }
            var jsonImages = JSONArray(jsonString)
            for (i in 0 until jsonImages.length()) {
                var imgLink = jsonImages.getJSONObject(i).getString("hdurl")
                list.add(imgLink)
            }
            rvAdapter.notifyDataSetChanged()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
