package com.huda.submission_3_made_bn.ui.tvshow

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.huda.submission_3_made_bn.R
import com.huda.submission_3_made_bn.ui.RootData
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_person"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        val data = intent.getParcelableExtra(EXTRA_FILM) as? RootData
        tv_item_name_detail.text = data?.name
        tv_item_description_detail.text = data?.description
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+data?.photo)
            .apply(RequestOptions().override(512, 512))
            .into(img_item_photo_detail)
        val percent = data?.rate!! * 10
        tv_nilai_rate_detail.text = percent.toString() + "%"
    }
}
