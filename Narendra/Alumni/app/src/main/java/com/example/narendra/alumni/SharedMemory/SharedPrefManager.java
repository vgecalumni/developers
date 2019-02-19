package com.example.narendra.alumni.SharedMemory;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.narendra.alumni.Model.SharedUser;
import com.example.narendra.alumni.Model.User;

public class SharedPrefManager {
    final static String SHARED_NAME="shared_user";

    private static SharedPrefManager mInstance;
    private Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getmInstance (Context context){
        if(mInstance==null){
            mInstance=new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveUser(User user){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        editor.putString("id",user.getId());
        editor.putString("fname",user.getFname());
        editor.putString("mname",user.getMname());
        editor.putString("lname",user.getLname());
        editor.putString("enroll",user.getEnroll());
        editor.putString("address",user.getAddress());
        editor.putString("district",user.getDistrict());
        editor.putString("pin",user.getPincode());
        editor.putString("city",user.getCity());
        editor.putString("state",user.getState());

        editor.apply();
    }

    public void saveSharedUser(SharedUser user){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        editor.putString("fname",user.getFname());
        editor.putString("mname",user.getMname());
        editor.putString("lname",user.getLname());
        editor.putString("address",user.getAddress());
        editor.putString("district",user.getDistrict());
        editor.putString("city",user.getCity());
        editor.putString("pin",user.getPincode());
        editor.putString("state",user.getState());

        editor.apply();
    }

    public SharedUser getSharedUser(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return new SharedUser(
                sharedPreferences.getString("id",null),
                sharedPreferences.getString("fname",null),
                sharedPreferences.getString("mname",null),
                sharedPreferences.getString("lname",null),
                sharedPreferences.getString("address",null),
                sharedPreferences.getString("district",null),
                sharedPreferences.getString("pin",null),
                sharedPreferences.getString("city",null),
                sharedPreferences.getString("state",null)

        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
