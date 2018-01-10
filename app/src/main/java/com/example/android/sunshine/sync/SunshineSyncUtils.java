package com.example.android.sunshine.sync;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

// COMPLETED (9) Create a class called SunshineSyncUtils
public class SunshineSyncUtils{

    //  COMPLETED (10) Create a public static void method called startImmediateSync
    //  COMPLETED (11) Within that method, start the SunshineSyncIntentService
    public static void startImmediateSync(Context context){
        Intent myIntent = new Intent(context,SunshineSyncIntentService.class);
        context.startService(myIntent);
    }
}
