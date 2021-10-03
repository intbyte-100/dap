package com.intkgc.dap.util

import android.content.Context
import android.graphics.Bitmap
import com.intkgc.dap.provider.ElementsProvider
import android.graphics.BitmapFactory
import java.io.File


import java.net.HttpURLConnection
import java.net.URL


class RepositoryResourceSpace(context: Context, name: String) {
    private val images = hashMapOf<Int, Bitmap>()
    private val pages = hashMapOf<String, ElementsProvider>()
    private val resourceDirectory = File(context.filesDir, name)

    init {
        if(!resourceDirectory.exists())
            resourceDirectory.mkdir()
    }

    fun savePage(markdown: String){
        TODO("not yet implemented")
    }

    fun saveImage(stringUrl: String){
        val connection = URL(stringUrl).openConnection() as HttpURLConnection
        val hashCode = stringUrl.hashCode()
        connection.doInput = true
        connection.connect()

        val input = connection.inputStream
        val bitmap = BitmapFactory.decodeStream(input)
        val file = File(resourceDirectory, "$hashCode.png")

        file.createNewFile()
        file.writeBitmap(bitmap, Bitmap.CompressFormat.PNG, 85)
        images[hashCode] = bitmap
    }

    fun getPage(path: String): ElementsProvider {
        TODO("not yet implemented")
    }

    fun getImage(path: String): Bitmap {
        val hashCode = path.hashCode()
        var image = images[hashCode]

        if (image == null)
            image = BitmapFactory.decodeFile("$resourceDirectory/$hashCode.png")
        
        images[hashCode] = image!!
        return image
    }
}