package com.example.narendra.alumni.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.narendra.alumni.Adapter.EducationAdapter;
import com.example.narendra.alumni.Add_Education;
import com.example.narendra.alumni.Api.RetrofitClient;
import com.example.narendra.alumni.Model.CustomDialog;
import com.example.narendra.alumni.Model.DefaultResponse;
import com.example.narendra.alumni.Model.EduHolder;
import com.example.narendra.alumni.Model.EduResponse;
import com.example.narendra.alumni.Model.Education;
import com.example.narendra.alumni.Model.Myinterface;
import com.example.narendra.alumni.Model.RecyclerTouchHelper;
import com.example.narendra.alumni.Model.SharedUser;
import com.example.narendra.alumni.Model.User;
import com.example.narendra.alumni.Profile;
import com.example.narendra.alumni.R;
import com.example.narendra.alumni.SharedMemory.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationInfo extends Fragment implements RecyclerTouchHelper.RecyclerTouchHelperListener, Myinterface, CustomDialog.CustomDialogListener {
    View v;
    List<Education> educationList;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    String s_id;
    private EducationAdapter adapter;
    private int pos = -1;
    private CustomDialog customDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPrefManager sharedPrefManager = SharedPrefManager.getmInstance(getContext());
        User user = sharedPrefManager.getUser();
        s_id = user.getId();

        ((Profile) Objects.requireNonNull(getContext())).setListner(this);

        v = inflater.inflate(R.layout.fragment_education_info, container, false);

        customDialog = new CustomDialog(getContext(), "Are you sure to delete?", this);
        linearLayout = v.findViewById(R.id.no_edu);

        recyclerView = v.findViewById(R.id.recyler_education);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this, getContext());
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        getEducation();

        return v;
    }

    public void getEducation() {
        Call<EduResponse> eduResponseCall = RetrofitClient.getInstance().getInterPreter().getAllEdu(s_id);
        eduResponseCall.enqueue(new Callback<EduResponse>() {
            @Override
            public void onResponse(Call<EduResponse> call, Response<EduResponse> response) {
                EduResponse eduResponse = response.body();
                if (eduResponse.isError()) {
                    linearLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    educationList = eduResponse.getEducation();
                    adapter = new EducationAdapter(educationList, getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<EduResponse> call, Throwable t) {
            }
        });
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
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, final int position) {
        if (direction == ItemTouchHelper.RIGHT) {
            customDialog.showDialog(position);
        } else {
            pos = position;
            EduHolder.getInstance().setEducation(educationList.get(position));
            Intent intent = new Intent(getContext(), Add_Education.class);
            intent.putExtra("for", 1);
            getActivity().startActivityForResult(intent, 100);
            //Objects.requireNonNull(getContext()).startac(intent);
        }
    }

    @Override
    public void myAction(int i) {
        if (i == 100 && EduHolder.getInstance().getEducation() != null) {
            if (pos != -1) {
                educationList.remove(pos);
                educationList.add(pos, EduHolder.getInstance().getEducation());
                adapter.editItem();
            } else {
                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
                //educationList.add(educationList.size(), EduHolder.getInstance().getEducation());
                //adapter.editItem(pos);
            }
        }
    }

    @Override
    public void onPositiveClick(DialogInterface dialogInterface, final int position) {
        dialogInterface.dismiss();

        String s_tag = educationList.get(position).getTag();
        final Education undoEdu = educationList.get(position);
        adapter.removeItem(position);
        Call<DefaultResponse> responseCall = RetrofitClient.getInstance().getInterPreter()
                .delEdu(s_id, s_tag);
        responseCall.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.body().isError()) {
                    Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                    adapter.undoItem(undoEdu, position);
                } else {
                    //
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                adapter.undoItem(undoEdu, position);
            }
        });
    }

    @Override
    public void onNegativeClick(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        adapter.editItem();
    }
}