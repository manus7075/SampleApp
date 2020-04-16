package com.example.newtestapplication.data.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newtestapplication.R
import com.example.newtestapplication.data.observe
import com.example.newtestapplication.data.presentation.adapter.PagerAdapter
import com.example.newtestapplication.data.Article
import com.example.newtestapplication.data.presentation.adapter.NewStaggeredAdapter
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : BaseActivity<NewsViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getViewModel().getTopNews()
        ivRefresh.setOnClickListener {
            getViewModel().getTopNews()
        }
        btTryAgain.setOnClickListener {
            getViewModel().getTopNews()
        }
    }


    private fun setStaggerdView(list:List<Article>){
      val layoutmanager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        recycler_view.layoutManager=layoutmanager
       recycler_view.adapter= NewStaggeredAdapter(list)


    }
    private fun setupAdapter(list: List<Article>) {
        newViewPager.adapter = PagerAdapter(list)
        indicator.setViewPager2(newViewPager)
        ivRefresh.visibility = View.VISIBLE
        indicator.visibility = View.VISIBLE
        cvNodata.visibility = View.GONE
    }

    override fun attachLiveData() {
        observe(getViewModel().progressLiveData) {
            if (it == true) {
                progress.visibility = View.VISIBLE
                cvNodata.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
            }
        }

        observe(getViewModel().errorLiveData) {
            cvNodata.visibility = View.VISIBLE
        }

        observe(getViewModel().getNewsLiveData()) {
            setStaggerdView(it.orEmpty())
           // setupAdapter(it.orEmpty())
        }
    }
}
