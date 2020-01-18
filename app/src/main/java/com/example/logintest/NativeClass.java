package com.example.logintest;

import android.util.Log;

public class NativeClass {
    private getDataFromNativeClass getDataFromNativeClass;
    NativeClass(getDataFromNativeClass getDataFromNativeClass){
        this.getDataFromNativeClass = getDataFromNativeClass;
    }
    static {
        System.loadLibrary("native-lib");
    }
   // public  native String stringFromJNI();
    public  native String setVerificationResponseFromJNI(String email,String password);

    public void setResponse(String result){
        Log.e("tag",result);
        String res;
        if(result.equals("1")){
            res = "Successful";
        }else {
            res = "Unsuccessful";
        }
        getDataFromNativeClass.getResult(res);
    }
}
