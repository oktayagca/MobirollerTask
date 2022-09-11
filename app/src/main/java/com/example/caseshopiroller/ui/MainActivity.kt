package com.example.caseshopiroller.ui

import com.example.caseshopiroller.R
import com.example.caseshopiroller.databinding.ActivityMainBinding
import com.example.caseshopiroller.ui.base.BaseActivity
import com.example.caseshopiroller.utils.gone
import com.example.caseshopiroller.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun themeSet() {
        setTheme(R.style.Theme_CaseShopiroller)
    }

    override fun onCreateActivity() {
    }

    fun setupToolbar(toolbar: androidx.appcompat.widget.Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    fun setToolbarTitle(title:String){
        supportActionBar!!.title = title
    }

    fun showLoadingProgressBar(showProgressBar:Boolean){
        if(showProgressBar){
            binding.progressBar.show()
            binding.navHostFragment.gone()
        }else{
            binding.progressBar.gone()
            binding.navHostFragment.show()
        }

    }


}