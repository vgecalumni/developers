package org.vgecalumni.SharedMemory;

import android.content.Context;
import android.content.SharedPreferences;

import org.vgecalumni.Model.SharedUser;
import org.vgecalumni.Model.User;

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
        editor.putString("email",user.getEmail());
        editor.putString("mobile",user.getMob());
        editor.putString("fname",user.getFname());
        editor.putString("mname",user.getMname());
        editor.putString("lname",user.getLname());
        editor.putString("branch",user.getBranch());
        editor.putString("enroll",user.getEnroll());
        editor.putString("gender",user.getGender());
        editor.putString("dob",user.getDob());
        editor.putString("address",user.getAddress());
        editor.putString("district",user.getDistrict());
        editor.putString("pin",user.getPincode());
        editor.putString("city",user.getCity());
        editor.putString("state",user.getState());
        editor.putString("pic",user.getPic());
        editor.putString("intro",user.getIntro());

        editor.apply();
    }

    public User getUser(){
        SharedPreferences user = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return new User(
                user.getString("id",null),
                user.getString("fname",null),
                user.getString("mname",null),
                user.getString("lname",null),
                user.getString("enroll",null),
                user.getString("branch",null),
                user.getString("gender",null),
                user.getString("dob",null),
                user.getString("email",null),
                user.getString("mobile",null),
                user.getString("address",null),
                user.getString("city",null),
                user.getString("pin",null),
                user.getString("district",null),
                user.getString("state",null),
                user.getString("pic",null),
                user.getString("intro",null)
        );
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
        editor.putString("pic",user.getPic());
        editor.putString("intro",user.getIntro());
        editor.putString("gender",user.getGen());
        editor.putString("dob",user.getDob());
        editor.apply();
    }

    public void clear(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
