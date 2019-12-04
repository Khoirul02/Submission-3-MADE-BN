package com.huda.submission_3_made_bn.ui.tvshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huda.submission_3_made_bn.ui.RootData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class TvShowViewModel : ViewModel() {

    companion object {
        private const val API_KEY = "8152b136a5dad36d2ca7844f884577ba"
    }

    val listDataFilm = MutableLiveData<ArrayList<RootData>>()

    internal fun setFilm() {
        val client = AsyncHttpClient()
        val listItems = ArrayList<RootData>()
        val url =
            "https://api.themoviedb.org/3/tv/popular?api_key=$API_KEY&language=en-US&page=1"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val results = responseObject.getJSONArray("results")
                    for (i in 0 until results.length()) {
                        val film = results.getJSONObject(i)
                        val filmItems = RootData()
                        filmItems.id = film.getInt("id")
                        filmItems.name = film.getString("original_name")
                        filmItems.description = film.getString("overview")
                        filmItems.photo = film.getString("poster_path")
                        listItems.add(filmItems)
                    }
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