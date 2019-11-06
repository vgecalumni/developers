package org.vgecalumni.Api;

import com.bumptech.glide.annotation.Excludes;

import org.vgecalumni.Model.DefaultResponse;
import org.vgecalumni.Model.EduResponse;
import org.vgecalumni.Model.ExprResponse;
import org.vgecalumni.Model.NewsResponse;
import org.vgecalumni.Model.RegEventResponse;
import org.vgecalumni.Model.UserResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterPreter {

    @GET("/android/getnews.php")
    Call<NewsResponse> getNews();

    @FormUrlEncoded
    @POST("/android/getUser.php")
    Call<UserResponse> getUser(
            @Field("uname") String uname
    );

    @FormUrlEncoded
    @POST("/android/notify.php")
    Call<DefaultResponse> notifyUsers(
            @Field("title") String title,
            @Field("body") String body,
            @Field("url") String url
    );

    @FormUrlEncoded
    @POST("/android/getRegEvent.php")
    Call<RegEventResponse> getRegEvents(
            @Field("uname") String uname
    );

    @FormUrlEncoded
    @POST("/android/updateCo.php")
    Call<DefaultResponse> updatecoupon(
            @Field("uname") String uname,
            @Field("coupon") String coupon
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

    @Multipart
    @POST("/android/uploadImage.php")
    Call<DefaultResponse> uploadImage(
            @Part("image\";filename=\"myfile.jpg\" ") RequestBody file,
            @Part("id") RequestBody id
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
            @Field("pincode") String pin,
            @Field("city") String city,
            @Field("state") String state,
            @Field("pic") String s_pic,
            @Field("intro") String S_intro,
            @Field("gender") String s_gen,
            @Field("dob") String s_dob);

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
