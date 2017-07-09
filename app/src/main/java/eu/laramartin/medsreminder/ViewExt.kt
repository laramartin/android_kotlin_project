package eu.laramartin.medsreminder

import android.view.View

fun View.visible(boolean: Boolean) {
    visibility = if (boolean) View.VISIBLE else View.INVISIBLE
}