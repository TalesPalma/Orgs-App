package com.talespalma.personaliseseuapp.tools

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder


object CoilTools {

   fun imageLoadGif(context:Context):ImageLoader{
       val imageLoad = ImageLoader.Builder(context)
           .components{
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                   add(ImageDecoderDecoder.Factory())
               }else{
                   add(GifDecoder.Factory())
               }
           }.build()
       return imageLoad
   }


}