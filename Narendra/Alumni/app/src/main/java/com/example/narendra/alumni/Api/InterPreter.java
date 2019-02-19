package com.example.narendra.alumni.Api;

import com.example.narendra.alumni.Model.DefaultResponse;
import com.example.narendra.alumni.Model.EduResponse;
import com.example.narendra.alumni.Model.ExprResponse;
import com.example.narendra.alumni.Model.UserResponse;
import com.example.narendra.alumni.Model.Test;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterPreter {

    @GET("/app_getuserdata.php")
    Call<Test> fetchuser(
    );

    @FormUrlEncoded
    @POST("/android/getUser.php")
    Call<UserResponse> getUser(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("/android/getAllEdu.php")
    Call<EduResponse> getAllEdu(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("/android/getAllExpr.php")
    Call<ExprResponse> getAllExpr(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("/android/editInfo.php")
    Call<DefaultResponse> editInfo(
            @Field("id") String id,
            @Field("fname") String fname,
            @Field("mname") String mname,
            @Field("lname") String lname,
            @Field("address") String address,
            @Field("district") String district,
            @Field("pin") String pin,
            @Field("city") String city,
            @Field("state") String state
    );

    @FormUrlEncoded
    @POST("/android/editEdu.php")
    Call<DefaultResponse> editEdu(
            @Field("id") String id,
            @Field("tag") String tag,
            @Field("inst") String inst,
            @Field("degree") String degree,
            @Field("stream") String stream,
            @Field("join") String join,
            @Field("end") String end
    );

    @FormUrlEncoded
    @POST("/android/editExpr.php")
    Call<DefaultResponse> editExpr(
            @Field("id") String id,
            @Field("tag") String tag,
            @Field("comp") String comp,
            @Field("desig") String desig,
            @Field("desc") String desc,
            @Field("join") String join,
            @Field("end") String end
    );

    @FormUrlEncoded
    @POST("/android/addEdu.php")
    Call<DefaultResponse> addEdu(
            @Field("id") String id,
            @Field("tag") String tag,
            @Field("inst") String inst,
            @Field("degree") String degree,
            @Field("stream") String stream,
            @Field("join") String join,
            @Field("end") String end
    );

    @FormUrlEncoded
    @POST("/android/addExpr.php")
    Call<DefaultResponse> addExpr(
            @Field("id") String id,
            @Field("tag") String tag,
            @Field("comp") String comp,
            @Field("desig") String desig,
            @Field("desc") String desc,
            @Field("join") String join,
            @Field("end") String end
    );

    @FormUrlEncoded
    @POST("/android/delEdu.php")
    Call<DefaultResponse> delEdu(
            @Field("id") String id,
            @Field("tag") String tag
    );

    @FormUrlEncoded
    @POST("/android/delExpr.php")
    Call<DefaultResponse> delExpr(
            @Field("id") String id,
            @Field("tag") String tag
    );
}
