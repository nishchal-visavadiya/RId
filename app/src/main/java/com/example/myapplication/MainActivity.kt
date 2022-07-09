package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.LayoutOneBinding
import com.example.myapplication.databinding.LayoutTwoBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mBindingOne: LayoutOneBinding
    private lateinit var mBindingTwo: LayoutTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        mBindingOne = LayoutOneBinding.inflate(layoutInflater, mBinding.mainView, true)
        mBindingTwo = LayoutTwoBinding.inflate(layoutInflater, mBinding.mainView, true)

        mBinding.click = this
        mBindingOne.click = this
        mBindingTwo.click = this
        setContentView(mBinding.root)
    }

    override fun onResume() {
        super.onResume()
        mBinding.textMain.text = getString(R.string.hello_world)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            mBindingTwo.btn1.id -> {
                ShowToast(layoutInflater.toString())
            }
            mBindingOne.btn1.id -> {
                Launch<ActivityNew>()
            }
            mBinding.textMain.id -> {
                mBinding.textMain.text = layoutInflater.toString()
            }
        }
    }
}