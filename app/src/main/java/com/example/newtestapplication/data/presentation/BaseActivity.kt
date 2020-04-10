package com.example.newtestapplication.data.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newtestapplication.R
import com.example.theaterbookapp.data.core.MyException
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    private lateinit var baseViewModel: T

    open fun getViewModel(): T {
        if (!::baseViewModel.isInitialized) {
            baseViewModel = ViewModelProviders.of(this)
                .get((this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>)
        }
        return baseViewModel
    }


    abstract fun attachLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
    }


    override fun onResume() {
        super.onResume()


        baseViewModel = getViewModel()

        baseViewModel.errorLiveData.observe(this, Observer {
            baseViewModel.progressLiveData.value = false
            it?.apply {
                if (it is MyException) {
                    when (it) {
                        is MyException.UnKnownError -> {
                            Toast.makeText(
                                this@BaseActivity,
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        is MyException.NetworkErrorError -> {
                            Toast.makeText(
                                this@BaseActivity,
                                getString(R.string.connection_error),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                    }


                } else {
                    Log.e("Error", it.localizedMessage, it)
                }
                baseViewModel.errorLiveData.value = null
            }
        })
        attachLiveData()
    }


    override fun onPause() {
        baseViewModel.errorLiveData.value = null
        baseViewModel.progressLiveData.value = false
        super.onPause()
    }

}