package com.lancer.codereview

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeResourceStream
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    companion object{

        fun decodeResource(res: Resources, id: Int, opts: BitmapFactory.Options?): Bitmap? {
            var bm: Bitmap? = null
            var `is`: InputStream? = null
            try {
                val value = TypedValue()
                `is` = res.openRawResource(id, value)
                bm = decodeResourceStream(res, value, `is`, null, opts)
            } catch (e: Exception) {
                /*  do nothing. If the exception happened on open, bm will be null.   If it happened on close, bm is still valid.        */
            } finally {
                try {
                    if (`is` != null) `is`.close()
                } catch (e: IOException) {            // Ignore
                }
            }
            require(!(bm == null && opts != null && opts.inBitmap != null)) { "Problem decoding into existing bitmap" }
            return bm
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun bitmapTest() {
        //bitmap加载的配置类
        val options = BitmapFactory.Options()
        val bitmap =
            BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background, options)
        //压缩方法：Bitmap.CompressFormat.JPEG 枚举类是用来设置压缩方式的, 压缩质量，取值0-100，0表示最低画质压缩，100表示最高画质压缩, 将压缩后的图片写到指定的输出流中
        //返回值：boolean， 返回true表示成功将bitmap压缩到输出流中，然后可以通过Bitmap.Factory从相应的输入流中解析出来bitmap信息
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
        //表示位图像素的存储格式，什么意思呢？  就是bitmap在屏幕上显示的每一像素在内存中存储的格式，会影响Bitmap真实图片的透明度以及图片质量
        //Bitmap.Config.ALPHA_8

    }
}