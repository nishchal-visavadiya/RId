package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityBinding

class ActivityNew : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBinding.inflate(layoutInflater)
        mBinding.click = this
        setContentView(mBinding.root)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            mBinding.text1.id -> {
                mBinding.text1.text = layoutInflater.toString()
                ShowToast(layoutInflater.toString())
            }
        }
    }

}

