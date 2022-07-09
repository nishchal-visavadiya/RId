package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun Context.ShowToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T: AppCompatActivity>AppCompatActivity.Launch(bundle: Bundle? = null) {
    startActivity(Intent(this, T::class.java), bundle)
}