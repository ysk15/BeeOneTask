package com.ysk.beeonetask.utils;

import android.content.Context;
import io.objectbox.BoxStore;

public class ObjectBox {
    private static BoxStore boxStore=null;
    public static void init(Context context) {
        if(boxStore==null){
            boxStore = MyObjectBox.builder()
                    .androidContext(context.getApplicationContext())
                    .build();
        }
    }
    public static BoxStore get() {
        return boxStore;
    }
}