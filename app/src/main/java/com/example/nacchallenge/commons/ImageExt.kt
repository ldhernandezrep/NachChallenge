package com.example.nacchallenge.commons

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.nacchallenge.R

@SuppressLint("SuspiciousIndentation")
fun ImageView.loadImageUrl(url: String, nameImage: String? = null, context: Context) {

    val requestOptions = RequestOptions()
        .centerCrop()
        .override(120)
        .error(
            if (!nameImage.isNullOrEmpty()) {
                generateTextDrawable(nameImage, context)
            } else {
                ContextCompat.getDrawable(context, R.drawable.place_holder)
            }
        )

    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this)

}

private fun generateTextDrawable(text: String, context: Context): Drawable {
    val color = Color.parseColor("#FFA500") // Puedes cambiar el color según tus preferencias

    val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.color = color
    canvas.drawCircle(50f, 50f, 50f, paint)

    val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    textPaint.color = Color.WHITE
    textPaint.textSize = 40f // Tamaño del texto
    textPaint.textAlign = Paint.Align.CENTER
    val x = canvas.width / 2f
    val y = (canvas.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2)
    canvas.drawText("${text.take(2).uppercase()} ", x, y, textPaint)

    return BitmapDrawable(context.resources, bitmap)
}