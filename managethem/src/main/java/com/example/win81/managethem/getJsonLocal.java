package com.example.win81.managethem;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import java.io.InputStream;

public class getJsonLocal {

    private ReflectResource resource;
    private String jsonName;
    private Context mContext;
    private StructThem myParsedJson;

    public StructThem getMyParsedJson() {
        return myParsedJson;
    }

    public getJsonLocal(Context mContext, ReflectResource resource, String jsonName) {
        this.mContext = mContext;
        this.jsonName = jsonName;
        this.resource = resource;
        getJsonFromRaw();
    }

    private void getJsonFromRaw() {
        InputStream strim = resource.getResApkRaw(jsonName);
        String finalJson = convertStreamToString(strim);
        Log.i("getJsonLocal: ", finalJson);
        makeJSON(finalJson);
    }

    private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private void makeJSON(String finalJson) {
        Gson gson = new Gson();
        myParsedJson = gson.fromJson(finalJson, StructThem.class);
    }
}
