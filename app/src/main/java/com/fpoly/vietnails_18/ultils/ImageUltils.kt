package com.fpoly.vietnails_18.ultils

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.util.Base64
import java.io.ByteArrayOutputStream


object ImageUltils {
    fun getEncoded64ImageStringFromBitmap(bitmap: Bitmap): String? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.JPEG, 70, stream)
        val byteFormat: ByteArray = stream.toByteArray()
        // get the base 64 string
        return Base64.encodeToString(byteFormat, Base64.NO_WRAP)
    }
}