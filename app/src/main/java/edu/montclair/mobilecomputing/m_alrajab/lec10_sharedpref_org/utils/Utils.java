package edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by m_alrajab on 2/22/17.
 */

public class Utils {

    public static final String SHARED_PREF_FILENAME="edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.SHAREDFILE1";
    public static final String KEY_TITLE="Title_";
    public static final String KEY_BODY="Body_";


    public static String[] getListFromSP(Context context, String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_FILENAME,
                Context.MODE_PRIVATE);
        Map<String, ?> map=sharedPreferences.getAll();
        ArrayList<String> lst= new ArrayList<>();
        for(String str:map.keySet()){
            if(str.startsWith(key))
                lst.add((String)map.get(str));
        }
        return lst.toArray(new String[lst.size()]);
    }

    public static String[] getListFromF(Context context, String key){

        // keep the same interface
        // this method will be called in two modes: when key string Title_ is passed return the list of filenames (Titles)
        // otherwise returm the content of the files (Bodies)

        Map<String, String> listOfFilesInMemory=new LinkedHashMap<>();

        try {
            File filesDir=context.getFilesDir();
            File[] files=filesDir.listFiles();
            for (File file:files) {
                String tempStr="";

                FileInputStream inputStream = context.openFileInput(file.getName());
                int c;
                while ( (c=inputStream.read()) != -1){
                    tempStr += Character.toString((char)c);
                    //tv.setText(tempStr);
                }
                inputStream.close();

                listOfFilesInMemory.put(file.getName(), tempStr);
            }

        }
        catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

        if ("Title_".equals(key)){
            return listOfFilesInMemory.keySet().toArray(new String[0]);
        }
        return listOfFilesInMemory.values().toArray(new String[0]);


    }
}
