package com.example.myapplication

import android.view.MotionEvent

class MyGestureDetector(private val mListener: MyGestureListener) {

    interface MyGestureListener {
        fun onDown(): Boolean
        fun onScroll(e1: MotionEvent?, e2: MotionEvent?): Boolean
        fun onDoubleTap(): Boolean
    }

    private var handled = false
    private var singleTapHandled = false
    private var goingToHandleGestures = false

    private var currentEvent: MotionEvent? = null
    private var previousEvent: MotionEvent? = null
    private var firstDownEvent: MotionEvent? = null
    private var previousDownEvent: MotionEvent? = null
    private var currentDownEvent: MotionEvent? = null
    private var previousUpEvent: MotionEvent? = null
    private var currentUpEvent: MotionEvent? = null
    private var previousMoveEvent: MotionEvent? = null
    private var currentMoveEvent: MotionEvent? = null

    private val mLock = Any()

    fun onTouchEvent(event: MotionEvent): Boolean {
        synchronized(mLock) {
            return onTouch(event)
        }
    }

    private fun onTouch(event: MotionEvent): Boolean {
        handled = false
        event.recycle()
        previousEvent = currentEvent
        currentEvent = MotionEvent.obtain(event)

        when (currentEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                previousDownEvent = currentDownEvent
                currentDownEvent = currentEvent
                if (firstDownEvent == null) firstDownEvent = currentDownEvent
                goingToHandleGestures = mListener.onDown()
                handled = goingToHandleGestures
            }
            MotionEvent.ACTION_MOVE -> {
                previousMoveEvent = currentMoveEvent
                currentMoveEvent = currentEvent
                if (!goingToHandleGestures) return handled

                if (System.currentTimeMillis() - currentDownEvent.time > DOUBLE_TAP_DETECTION_TIME) {
                    handled = mListener.onScroll(previousUpEvent, currentMoveEvent)
                }
            }
            MotionEvent.ACTION_UP -> {
                previousUpEvent = currentUpEvent
                currentUpEvent = currentEvent
                if (!goingToHandleGestures) return handled
                if (singleTapHandled && (currentUpEvent.time - (firstDownEvent?.eventTime ?: Long.MIN_VALUE) < DOUBLE_TAP_DETECTION_TIME)) {
                    handled = mListener.onDoubleTap()
                    if (handled) restAll()
                }
                if (currentUpEvent.time == firstDownEvent.time) { singleTapHandled = true }
            }
            MotionEvent.ACTION_CANCEL -> {
                restAll()
            }
        }
        return handled
    }

    private fun restAll() {
        handled = false
        singleTapHandled = false
        goingToHandleGestures = false
        currentEvent = null
        previousEvent = null
        firstDownEvent = null
        previousDownEvent = null
        currentDownEvent = null
        previousUpEvent = null
        currentUpEvent = null
        previousMoveEvent = null
        currentMoveEvent = null
    }

    companion object {

        private const val DOUBLE_TAP_DETECTION_TIME = 100L

    }

}

val MotionEvent?.time: Long
    get() = this?.eventTime ?: 0L