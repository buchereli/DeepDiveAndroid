package com.buchereli.deepdiveandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by buche on 8/25/2017.
 */

public class AssetManager {

    public static int scale = 2;
    private final static HashMap<Integer, Bitmap> images = new HashMap<>();
    private final static BitmapFactory.Options options = new BitmapFactory.Options();

    static {
        options.inScaled = false;
    }

    public static void load(Context context, ArrayList<Integer> ids) {
        for (int id : ids) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), id, options);
            bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth() * scale, bmp.getHeight() * scale, false);
            images.put(id, bmp);
        }
    }

    public static Bitmap get(int id) {
        return images.get(id);
    }


}
