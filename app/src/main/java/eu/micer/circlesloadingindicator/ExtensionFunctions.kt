package eu.micer.circlesloadingindicator

import android.content.res.Resources

/**
 * Converts dp to pixel
 */
val Int.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/**
 * Converts pixel to dp
 */
val Int.pxToDp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()