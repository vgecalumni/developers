package org.vgecalumni.Components.Profile;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Model.DefaultResponse;
import org.vgecalumni.Model.Function;
import org.vgecalumni.Model.SharedUser;
import org.vgecalumni.Model.User;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Edit extends AppCompatActivity implements View.OnClickListener {

    private AppCompatEditText fname, mname, lname, address, district, pin, city, state, intro, dob;
    private TextInputLayout l_fname, l_mname, l_lname, l_address, l_district, l_pin, l_city, l_state, l_intro, l_dob;
    private String s_id, s_fname, s_mname, s_lname, s_address, s_district, s_pin, s_city, s_state, s_pic, s_intro, s_gen, s_dob;
    private CircleImageView profile;
    RadioGroup gender;
    TextView rem;
    RelativeLayout layout;
    private Uri fileuri;
    private String path = null;
    File file = null;
    String type = null;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_profile_edit);
        setTitle("Basic Information");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fname = findViewById(R.id.user_firstname);
        mname = findViewById(R.id.user_midlename);
        lname = findViewById(R.id.user_lastname);

        address = findViewById(R.id.user_address);
        district = findViewById(R.id.user_district);
        pin = findViewById(R.id.user_pincode);
        city = findViewById(R.id.user_city);
        state = findViewById(R.id.user_state);
        intro = findViewById(R.id.user_intro);
        gender = findViewById(R.id.rdb_gen);
        dob = findViewById(R.id.user_dob);
        dob.setOnClickListener(this);

        l_fname = findViewById(R.id.lay_firstname);
        l_mname = findViewById(R.id.lay_midlename);
        l_lname = findViewById(R.id.lay_lastname);
        l_dob = findViewById(R.id.lay_dob);

        l_address = findViewById(R.id.lay_address);
        l_district = findViewById(R.id.lay_district);
        l_pin = findViewById(R.id.lay_pincode);
        l_city = findViewById(R.id.lay_city);
        l_state = findViewById(R.id.lay_state);
        l_intro = findViewById(R.id.lay_intro);

        layout = findViewById(R.id.lay_progress);

        profile = findViewById(R.id.user_profile);
        rem = findViewById(R.id.rm_profile);

        rem.setOnClickListener(this);
        profile.setOnClickListener(this);

        getDetails();
    }

    private void getDetails() {
        SharedPrefManager sharedPrefManager = SharedPrefManager.getmInstance(getApplicationContext());
        User user = sharedPrefManager.getUser();

        s_id = user.getId();
        s_pic = user.getPic();
        s_intro = user.getIntro();

        if (!s_pic.equals("")) {
            Glide.with(this).load(s_pic).into(profile);
        }

        fname.setText(user.getFname());
        mname.setText(user.getMname());
        lname.setText(user.getLname());
        dob.setText(user.getDob());

        address.setText(user.getAddress());
        district.setText(user.getDistrict());
        pin.setText(user.getPincode());
        city.setText(user.getCity());
        state.setText(user.getState());

        intro.setText(user.getIntro());
        gender.check(Function.getGender(user.getGender()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_save:
                if (validate()) {
                    clearError();
                    showProgress();
                    if (fileuri == null && path == null) {
                        uploadData();
                    } else {
                        uploadProfile();
                    }
                }
                break;
        }
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadData() {
        Call<DefaultResponse> responseCall = RetrofitClient.getInstance()
                .getInterPreter().editInfo(s_id, s_fname, s_mname, s_lname, s_address, s_district, s_pin, s_city, s_state, s_pic, s_intro, s_gen, s_dob);
        responseCall.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse response1 = response.body();
                endProgress();
                if (response1.isError()) {
                    Toast.makeText(Profile_Edit.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Profile_Edit.this, "Profile updated", Toast.LENGTH_SHORT).show();
                    sharedPrefManager = SharedPrefManager.getmInstance(getApplicationContext());
                    SharedUser sharedUser = new SharedUser(s_id, s_fname, s_mname, s_lname, s_address, s_district,
                            s_pin, s_city, s_state, s_pic, s_intro, s_gen, s_dob);
                    sharedPrefManager.saveSharedUser(sharedUser);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                endProgress();
                Toast.makeText(Profile_Edit.this, "Failure Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadProfile() {
        RequestBody requestBody = RequestBody.create(MediaType.parse(type), file);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("text/plain"), s_id);

        Call<DefaultResponse> call = RetrofitClient.getInstance().getInterPreter().uploadImage(requestBody, requestBody2);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse response1 = response.body();
                if (response1.isError()) {
                    Toast.makeText(Profile_Edit.this, "Error while Image Uploading", Toast.LENGTH_SHORT).show();
                    uploadData();
                } else {
                    s_pic = response1.getMessage();
                    uploadData();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(Profile_Edit.this, "Error while Image Uploading", Toast.LENGTH_SHORT).show();
                uploadData();
            }
        });
    }

    private void clearError() {
        l_fname.setError(null);
        l_mname.setError(null);
        l_lname.setError(null);

        l_address.setError(null);
        l_district.setError(null);
        l_pin.setError(null);
        l_city.setError(null);
        l_state.setError(null);

        l_intro.setError(null);
    }

    private boolean validate() {
        boolean x = true;
        clearError();

        s_fname = fname.getText().toString().trim();
        s_mname = mname.getText().toString().trim();
        s_lname = lname.getText().toString().trim();
        s_intro = intro.getText().toString().trim();
        s_dob = dob.getText().toString().trim();

        s_gen = Function.setGender(gender.getCheckedRadioButtonId());

        s_district = district.getText().toString().trim();
        s_pin = pin.getText().toString().trim();
        s_address = address.getText().toString().trim();
        s_city = city.getText().toString().trim();
        s_state = state.getText().toString().trim();

        String er_fname = Function.checkName(s_fname);
        String er_mname = Function.empty(s_mname);
        String er_lname = Function.checkName(s_lname);
        String er_district = Function.checkAddress(s_district);
        String er_address = Function.checkAddress(s_address);
        String er_pin = Function.checkpin(s_pin);
        String er_city = Function.empty(s_city);
        String er_state = Function.empty(s_state);
        String er_intro = Function.checkIntro(s_intro);
        /* String er_enroll = Function.checkEnroll(s_enroll);*/
        String er_dob = Function.checkDOB(s_dob);

        if (er_fname != null) {
            l_fname.setError(er_fname);
            x = false;
        }
        if (er_mname != null) {
            l_mname.setError(er_mname);
            x = false;
        }
        if (er_lname != null) {
            l_lname.setError(er_lname);
            x = false;
        }
        if (er_address != null) {
            l_address.setError(er_address);
            x = false;
        }
        if (er_district != null) {
            l_district.setError(er_district);
            x = false;
        }
        if (er_pin != null) {
            l_pin.setError(er_pin);
            x = false;
        }
        if (er_city != null) {
            l_city.setError(er_city);
            x = false;
        }
        if (er_state != null) {
            l_state.setError(er_state);
            x = false;
        }
        if (er_intro != null) {
            l_intro.setError(er_intro);
            x = false;
        }
        if (er_dob != null) {
            l_dob.setError(er_dob);
            x = false;
        }
        return x;
    }

    private void showProgress() {
        layout.setVisibility(View.VISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void endProgress() {
        layout.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            fileuri = data.getData();
            try {
                path = getRealpath(fileuri);
                if (fileuri != null && path == null) {
                    file = new File(fileuri.getPath());
                    type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(fileuri.toString()));
                } else if (path != null) {
                    file = new File(path);
                    type = getContentResolver().getType(fileuri);
                }
                if (type.startsWith("image")) {
                    Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), fileuri);
                    profile.setImageBitmap(image);
                } else {
                    Toast.makeText(this, "Invalid Image", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                Toast.makeText(this, "Problem", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getRealpath(Uri fileuri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(Profile_Edit.this, fileuri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        if (cursor != null) {
            int column = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();

            String result = cursor.getString(column);
            cursor.close();
            return result;
        } else {
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_profile:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent;
                        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 101);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                } else {
                    Intent intent;
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 101);
                }
                break;
            case R.id.rm_profile:
                s_pic = "";
                fileuri = null;
                path = null;
                profile.setImageResource(0);
                break;
            case R.id.user_dob:
                getDate(dob);
                break;
        }
    }

    private void getDate(final AppCompatEditText date) {
        final Calendar c = Calendar.getInstance();
        int y, m, d;
        y = c.get(Calendar.YEAR);
        m = c.get(Calendar.MONTH);
        d = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dt = (year + "-" + (month + 1) + "-" + dayOfMonth);
                date.setText(dt);
            }
        }, y, m, d);
        datePickerDialog.show();
    }
}