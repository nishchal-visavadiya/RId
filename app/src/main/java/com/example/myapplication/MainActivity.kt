package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.LayoutOneBinding
import com.example.myapplication.databinding.LayoutTwoBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mBindingOne: LayoutOneBinding
    private lateinit var mBindingTwo: LayoutTwoBinding
    private val tag = "MAIN"

    private val gsL1 = object : GestureDetector.OnGestureListener {
        @SuppressLint("SetTextI18n")
        override fun onDown(e: MotionEvent?): Boolean {
            mBinding.txt1.text = "gsL1onDown $e"
            Log.e(tag, "gsL1onDown $e")
            return true
        }
        @SuppressLint("SetTextI18n")
        override fun onShowPress(e: MotionEvent?) {
            mBinding.txt1.text = "gsL1onShowPress $e"
            Log.e(tag, "gsL1onShowPress $e")
        }
        @SuppressLint("SetTextI18n")
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            mBinding.txt1.text = "gsL1onSingleTapUp $e"
            Log.e(tag, "gsL1onSingleTapUp $e")
            return false
        }
        @SuppressLint("SetTextI18n")
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            mBinding.txt1.text = "gsL1onScroll $e2"
            Log.e(tag, "gsL1onScroll $e2")
            return true
        }
        @SuppressLint("SetTextI18n")
        override fun onLongPress(e: MotionEvent?) {
            mBinding.txt1.text = "gsL1onLongPress $e"
            Log.e(tag, "gsL1onLongPress $e")
        }
        @SuppressLint("SetTextI18n")
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            mBinding.txt1.text = "gsL1onFling $e2"
            Log.e(tag, "gsL1onFling $e2")
            return false
        }
    }

    private val gsL2 = object : GestureDetector.OnGestureListener {
        @SuppressLint("SetTextI18n")
        override fun onDown(e: MotionEvent?): Boolean {
            mBinding.txt2.text = "gsL2onDown $e"
            Log.e(tag, "gsL2onDown $e")
            return true
        }
        @SuppressLint("SetTextI18n")
        override fun onShowPress(e: MotionEvent?) {
            mBinding.txt2.text = "gsL2onShowPress $e"
            Log.e(tag, "gsL2onShowPress $e")
        }
        @SuppressLint("SetTextI18n")
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            mBinding.txt2.text = "gsL2onSingleTapUp $e"
            Log.e(tag, "gsL2onSingleTapUp $e")
            return false
        }
        @SuppressLint("SetTextI18n")
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            mBinding.txt2.text = "gsL2onScroll $e2"
            Log.e(tag, "gsL2onScroll $e2")
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            Log.e(tag, "gsL2onLongPress $e")
        }
        @SuppressLint("SetTextI18n")
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            mBinding.txt2.text = "gsL2onFling $e2"
            Log.e(tag, "gsL2onFling $e2")
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
//        mBindingOne = LayoutOneBinding.inflate(layoutInflater, mBinding.mainView, true)
//        mBindingTwo = LayoutTwoBinding.inflate(layoutInflater, mBinding.mainView, true)

//        mBinding.click = this
//        mBindingOne.click = this
//        mBindingTwo.click = this
        setContentView(mBinding.root)
        setTouch()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouch() {
        val gs1 = GestureDetector(this, gsL1)
        val gs2 = GestureDetector(this, gsL2)
        mBinding.img1.setOnTouchListener { v, event ->
            gs1.onTouchEvent(event)
        }
        mBinding.img2.setOnTouchListener { v, event ->
            gs2.onTouchEvent(event)
        }
    }

    override fun onResume() {
        super.onResume()
//        mBinding.textMain.text = getString(R.string.hello_world)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            mBindingTwo.btn1.id -> {
                ShowToast(layoutInflater.toString())
            }
            mBindingOne.btn1.id -> {
                Launch<ActivityNew>()
            }
//            mBinding.textMain.id -> {
//                mBinding.textMain.text = layoutInflater.toString()
//            }
        }
    }

}