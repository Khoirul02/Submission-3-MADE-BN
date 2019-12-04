package com.huda.submission_3_made_bn.ui.movie

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.huda.submission_3_made_bn.ui.RootData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MovieViewModelDetail : ViewModel() {
    companion object {
        private const val API_KEY = "8152b136a5dad36d2ca7844f884577ba"
    }
    private val mData = ArrayList<RootData>()
    fun setData(items: ArrayList<RootData>) {
        mData.clear()
        mData.addAll(items)
    }

    val listDataFilm = MutableLiveData<ArrayList<RootData>>()

    internal fun setFilm(
        context: Context,
        id: Int,
        name: TextView,
        description: TextView,
        image: ImageView,
        rateing : TextView
    ) {
        val client = AsyncHttpClient()
        val listItems = ArrayList<RootData>()
        val url =
            "https://api.themoviedb.org/3/movie/$id?api_key=$API_KEY"
        client.get(url, object : AsyncHttpResponseHandler() {
            @SuppressLint("SetTextI18n")
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
//                    val originaltittle = responseObject.getString("original_title")
//                    val overview = responseObject.getString("overview")
//                    description.text = overview
//                    val photo = responseObject.getString("poster_path")
//                    Glide.with(context)
//                        .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+photo)
//                        .override(512, 512).into(image)
//                    val rate = responseObject.getDouble("vote_average")
//                    val percent = rate * 10
//                    rateing.text = percent.toString()+"%"
                    val filmItems = RootData()
                    filmItems.name = responseObject.getString("original_title")
                    name.text = filmItems.name
                    filmItems.description = responseObject.getString("overview")
                    description.text = filmItems.description
                    filmItems.photo = responseObject.getString("poster_path")
                    Glide.with(context)
                        .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+filmItems.photo)
                        .override(512, 512).into(image)
                    filmItems.rate = responseObject.getDouble("vote_average")
                    val percent = filmItems.rate * 10
                    rateing.text = percent.toString()+"%"
                    listItems.add(filmItems)
                    listDataFilm.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    internal fun getFilm(): LiveData<ArrayList<RootData>> {
        return listDataFilm
    }
}