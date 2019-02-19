package com.example.narendra.alumni.Model;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;

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

    public static String checkDateGap(String string, String string2){
        String res=null;
        if(string.isEmpty() || string2.isEmpty()){
            res="Field Should Not Be Empty";
        } else {
            Date date = null;
            Date date2 = null;
            try {
                date=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(string);
                date2=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(string2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(date.equals(date2)){
                res="Date Should not be Same";
            }
        }
        return res;
    }

    public static String checkPresent(String string){
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
            Date ddate= Calendar.getInstance().getTime();
            if(date.equals(ddate)){
                res="Date Should not be Today";
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

    public static String checkEmail(String  string){
        String res=null;
        if(string.isEmpty()){
            res="Field Should Not be Empty";
        }else if (!Patterns.EMAIL_ADDRESS.matcher(string).matches()){
            res="Invalid Email Address";
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

    public static String setNotSpecify(@Nullable String string){
        String rep=null;
        if(string==null||string.equals("")){
            rep="Not Specified";
        }
        return rep;
    }
}