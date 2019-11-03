package org.vgecalumni.Components.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.vgecalumni.Components.Profile.Profile;
import org.vgecalumni.Model.Myinterface;
import org.vgecalumni.Model.User;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.Objects;

public class BasicInfo extends Fragment implements Myinterface {

    NestedScrollView scrollView;
    View v;

    TextView t_fullname, t_enroll, t_branch, t_gender, t_dob, t_mob, t_email, t_addr, t_addr2, t_addr3, t_intro;
    ImageView imageView;

    String s_fname, s_mname, s_lname, s_enroll, s_id, s_branch, s_gender, s_dob;
    String s_email, s_mob, s_address, s_district, s_pincode, s_city, s_state;
    String s_fullname, s_addr2, s_intro, s_pic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.profile_fragment_basic_info, container, false);

        ((Profile) Objects.requireNonNull(getActivity())).setListner3(this);

        t_email = ((Profile) Objects.requireNonNull(getActivity())).getT_email();
        t_mob = ((Profile) Objects.requireNonNull(getActivity())).getT_mob();
        imageView = ((Profile) Objects.requireNonNull(getActivity())).getImageView();

        t_fullname = v.findViewById(R.id.user_name);
        t_enroll = v.findViewById(R.id.user_enroll);
        t_branch = v.findViewById(R.id.user_branch);
        t_gender = v.findViewById(R.id.user_gender);
        t_dob = v.findViewById(R.id.user_dob);
        t_addr = v.findViewById(R.id.user_addr);
        t_addr2 = v.findViewById(R.id.user_addr2);
        t_addr3 = v.findViewById(R.id.user_addr3);
        t_intro = v.findViewById(R.id.user_intro);

        /*scrollView = v.findViewById(R.id.scroll);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int diff = (v.getChildAt(v.getChildCount() - 1)).getMeasuredHeight() - v.getMeasuredHeight();
                if (scrollY>= diff && scrollY>oldScrollY) {
                    boolean state = ((Profile) Objects.requireNonNull(getActivity())).getFabState();
                    if (state) {
                        ((Profile) Objects.requireNonNull(getActivity())).hideFab();
                    }
                } else {
                    boolean state = ((Profile) Objects.requireNonNull(getActivity())).getFabState();
                    if (!state) {
                        ((Profile) Objects.requireNonNull(getActivity())).showFab();
                    }
                }
            }
        });*/
        getDetails();
        return v;
    }

    private void getDetails() {
        SharedPrefManager sharedPrefManager = SharedPrefManager.getmInstance(getContext());
        User user = sharedPrefManager.getUser();

        s_id = user.getId();

        s_pic = user.getPic();
        s_email = user.getEmail();
        s_mob = user.getMob();

        s_fname = user.getFname();
        s_mname = user.getMname();
        s_lname = user.getLname();
        s_enroll = user.getEnroll();
        s_branch = user.getBranch();
        s_gender = user.getGender();
        s_dob = user.getDob();

        s_city = user.getCity();
        s_district = user.getDistrict();
        s_state = user.getState();
        s_pincode = user.getPincode();
        s_address = user.getAddress();

        s_intro = user.getIntro();

        s_fullname = s_fname + " " + s_mname + " " + s_lname;
        s_addr2 = s_district + "," + s_state + "-" + s_pincode;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setField();
            }
        });
    }

    private void setField() {
        if (!s_pic.isEmpty()) {
            Glide.with(getContext()).load(s_pic).placeholder(R.mipmap.vgec_applauncher_logo).dontAnimate().into(imageView);
        } else {
            imageView.setImageResource(R.mipmap.vgec_applauncher_logo);
        }
        t_email.setText(s_email);
        t_mob.setText(s_mob);

        t_fullname.setText(s_fullname);
        t_enroll.setText(s_enroll);
        t_branch.setText(s_branch);
        t_gender.setText(s_gender);
        t_dob.setText(s_dob);

        t_addr.setText(s_city);
        t_addr2.setText(s_addr2);
        t_addr3.setText(s_address);

        if (s_intro != null && !s_intro.equals("")) {
            t_intro.setText(s_intro);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void myAction(int i) {
        if (i == 300) {
            getDetails();
        }
    }
}
