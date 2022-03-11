package com.example.news


import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.intellij.lang.annotations.JdkConstants


class MainActivity : AppCompatActivity() , NewsItemClicked {
    private  lateinit var mAdapter: NewsListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView=findViewById<RecyclerView>(R.id.showing)
        recyclerView.layoutManager=LinearLayoutManager(this)
        fatchdata()
        mAdapter=NewsListAdapter(this)
        recyclerView.adapter=mAdapter
    }


    private fun fatchdata() {
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET ,
            url , null ,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title") ,
                        newsJsonObject.getString("author") ,
                        newsJsonObject.getString("url") ,
                        newsJsonObject.getString("urlToImage") ,
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            } ,
            {

            }
        )


        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onItemClicked(item: News) {

        val builder =  CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}