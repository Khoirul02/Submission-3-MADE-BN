package com.huda.submission_3_made_bn.ui.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.huda.submission_3_made_bn.R
import com.huda.submission_3_made_bn.ui.RootData
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var mainViewModel: TvShowViewModelDetail

    companion object {
        const val EXTRA_FILM = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TvShowViewModelDetail::class.java)
        val data = intent.getParcelableExtra(EXTRA_FILM) as? RootData
        val id = data?.id
        showLoading(true)
        if (id != null) {
            mainViewModel.setFilm(this, id, tv_item_name_detail, tv_item_description_detail, img_item_photo_detail,tv_nilai_rate_detail)
        }
        mainViewModel.getFilm().observe(this, Observer { filmItems ->
            if (filmItems != null) {
//                mainViewModel.setData(filmItems)
                showLoading(false)
            }
        })
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            progressBarDetail.visibility = View.VISIBLE
            img_item_photo_detail.visibility = View.GONE
            tv_item_name_detail.visibility = View.GONE
            text_rate.visibility = View.GONE
            tv_nilai_rate_detail.visibility = View.GONE
            tv_item_description_detail.visibility = View.GONE
        } else {
            progressBarDetail.visibility = View.GONE
            text_rate.visibility = View.VISIBLE
            img_item_photo_detail.visibility = View.VISIBLE
            tv_item_name_detail.visibility = View.VISIBLE
            text_rate.visibility = View.VISIBLE
            tv_nilai_rate_detail.visibility = View.VISIBLE
            tv_item_description_detail.visibility = View.VISIBLE

        }
    }
}
