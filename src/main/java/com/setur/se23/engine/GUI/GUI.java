package com.setur.se23.engine.GUI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.debug.Debug;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;


public class GUI {
    

    public static byte[] convertImageToByteArray(String imagePath) {

        Image img = null;

        try {
            img = new Image(new FileInputStream(Core.getResorcePath("sprites/flappy-bird.png")));
        }
        catch(FileNotFoundException e) {
            Core.debug.error("File not found");
        }

        int w = (int)img.getWidth();
        int h = (int)img.getHeight();

        // Create a new Byte Buffer, but we'll use BGRA (1 byte for each channel) //

        byte[] image = new byte[w * h * 4];

        /* Since you can get the output in whatever format with a WritablePixelFormat,
        we'll use an already created one for ease-of-use. */

        img.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), image, 0, w * 4);

        /* Second last parameter is byte offset you want to start in your buffer,
        and the last parameter is stride (in bytes) per line for your buffer. */

        return image;
    }
}
