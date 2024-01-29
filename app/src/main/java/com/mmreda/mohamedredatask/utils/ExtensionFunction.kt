package com.mmreda.mohamedredatask.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.makeToastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.makeToastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.makeSnackBarShort(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}


