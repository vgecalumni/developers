package com.example.narendra.alumni.Model;
import android.support.annotation.Nullable;
import android.util.Patterns;

import com.example.narendra.alumni.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Function {

    public static String checkName(String string){
        String res=null;
        if(string.isEmpty()){
            res="Field Should Not be Empty";
        }else if (string.matches("[\\s]")){
            res="Name Only have in One Word";
        }else if (!string.matches("^[a-zA-Z]{3,12}$")){
            res="Invalid Data";
        }
        return res;
    }

    public static String checkDOB(String string){
        String res=null;
        if(string.isEmpty()){
            res="Field Should Not Be Empty";
        } else {
            Date date = null;
            try {
                date=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date1= Calendar.getInstance().getTime();
            if(date1.before(date)){
                res="Date is Day Before Today";
            }
        }
        return res;
    }

    public static String checkEnroll(String  string){
        String res=null;
        if(string.isEmpty()){
            res="Field Should Not be Empty";
        }else if (string.length()!=12){
            res="Enrollment Length Must Be 12";
        }else if (!string.matches("^[0-9]{12}$")){
            res="Invalid Enrollment";
        }
        return res;
    }

    public static String checkpin(String  string){
        String res=null;
        if(string.isEmpty()){
            res="Field Should Not be Empty";
        }else if (string.length()!=6){
            res="Pincode Length Must Be 6";
        }else if (!string.matches("^[0-9]{6}$")){
            res="Invalid Pincode";
        }
        return res;
    }

    public static String checkYr(String  string){
        String res=null;
        int yr = Calendar.getInstance().get(Calendar.YEAR);
        if(string.isEmpty()){
            res="Field Should Not be Empty";
        }else if (string.length()!=4){
            res="Year Length Must Be 4";
        }else if(Integer.parseInt(string)>yr){
            res="Year must not be greater than current year";
        }else if (!string.matches("^[0-9]{4}$")){
            res="Invalid Year";
        }
        return res;
    }

    public static String checkMobile(String  string){
        String res=null;
        if(string.isEmpty()){
            res="Field Should Not be Empty";
        }else if (string.length()!=10){
            res="Mobile No. Length Must Be 10";
        }else if (!string.matches("^[0-9]{10}$")){
            res="Invalid Mobile No.";
        }
        return res;
    }

    public static String checkAddress(String  string){
        String res=null;
        if(string.isEmpty()){
            res="Field Should Not be Empty";
        }else if (!string.matches("^[\\s0-9a-zA-Z._'-,]*$")){
            res="Invalid Address Inputs";
        }
        return res;
    }

    public static String empty(String  string){
        String res=null;
        if(string.isEmpty()){
            res="Field Should Not be Empty";
        }else if (!string.matches("^[\\s0-9a-zA-Z.]*$")){
            res="Invalid Inputs";
        }
        return res;
    }

    public static String checkIntro(String string) {
        String res=null;
        if (!string.matches("^[\\s0-9a-zA-Z._'-,]*$")){
            res="Invalid Inputs In Field";
        }
        return res;
    }

    public static String setGender(int string){
        String res=null;
        if (string == R.id.user_gen_female){
            res="Female";
        }else {
            res="Male";
        }
        return res;
    }

    public static int getGender(String string){
        int res=R.id.user_gen_male;
        if (string.equalsIgnoreCase("male")){
            res=R.id.user_gen_male;
        }else {
            res=R.id.user_gen_female;
        }
        return res;
    }
}