package com.intkgc.dap.util

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
}

