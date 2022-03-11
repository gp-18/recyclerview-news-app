package com.example.news

import android.content.Context
import android.provider.CalendarContract
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class MySingleton constructor(context: Context) {
    companion object {
        @Volatile
        private var instance: MySingleton? = null

//        private fun Singleton() {}

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: MySingleton(context).also {
                    instance = it
                }
            }
    }

private  val requestQueue:RequestQueue by lazy {
    Volley.newRequestQueue(context.applicationContext)
}
fun<T> addToRequestQueue(req:Request<T>)
{
    requestQueue.add(req)
}
}

