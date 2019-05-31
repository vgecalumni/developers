package org.vgecalumni.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

import org.vgecalumni.Components.Profile.Add_Education;
import org.vgecalumni.Components.Profile.Add_Experience;
import org.vgecalumni.Adpater.ExperienceAdpter;
import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Components.Profile.Education_List;
import org.vgecalumni.Components.Profile.Experience_List;
import org.vgecalumni.Model.CustomDialog;
import org.vgecalumni.Model.DefaultResponse;
import org.vgecalumni.Model.EduHolder;
import org.vgecalumni.Model.Education;
import org.vgecalumni.Model.Experience;
import org.vgecalumni.Model.ExprHolder;
import org.vgecalumni.Model.ExprResponse;
import org.vgecalumni.Model.Myinterface;
import org.vgecalumni.Model.RecyclerTouchHelper;
import org.vgecalumni.Model.SharedUser;
import org.vgecalumni.Model.User;
import org.vgecalumni.Components.Profile.Profile;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExperienceInfo extends Fragment implements RecyclerTouchHelper.RecyclerTouchHelperListener, Myinterface, CustomDialog.CustomDialogListener {
    View v;
    List<Experience> experienceList;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    String s_id;
    private ExperienceAdpter adapter;
    private CustomDialog customDialog;
    private int pos = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPrefManager sharedPrefManager = SharedPrefManager.getmInstance(getContext());
        User user = sharedPrefManager.getUser();
        s_id = user.getId();

        ((Profile) getContext()).setListner2(this);

        v = inflater.inflate(R.layout.profile_fragment_experiance_info, container, false);

        linearLayout = v.findViewById(R.id.no_expr);
        customDialog = new CustomDialog(getContext(), "Are you sure to delete?", this);

        recyclerView = v.findViewById(R.id.recyler_experience);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this, getContext());
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        getExperience();

        return v;
    }

    private void getExperience() {
        Call<ExprResponse> exprResponseCall = RetrofitClient.getInstance().getInterPreter().getAllExpr(s_id);
        exprResponseCall.enqueue(new Callback<ExprResponse>() {
            @Override
            public void onResponse(Call<ExprResponse> call, Response<ExprResponse> response) {
                ExprResponse exprResponse = response.body();
                if (exprResponse.isError()) {
                    linearLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);

                    experienceList = exprResponse.getExperience();
                    adapter = new ExperienceAdpter(experienceList, getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ExprResponse> call, Throwable t) {
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
            ExprHolder.getInstance().setExperience(experienceList.get(position));
            Intent intent = new Intent(getContext(), Add_Experience.class);
            intent.putExtra("for", 1);
            getActivity().startActivityForResult(intent, 200);
        }
    }

    @Override
    public void myAction(int i) {
        if (i == 200 && ExprHolder.getInstance().getExperience() != null) {
            if (pos != -1) {
                experienceList.remove(pos);
                experienceList.add(pos, ExprHolder.getInstance().getExperience());
                adapter.editItem();
            } else {
                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
                //experienceList.add(experienceList.size(), ExprHolder.getInstance().getExperience());
                //adapter.editItem(pos);
            }
        }
    }

    @Override
    public void onPositiveClick(DialogInterface dialogInterface, final int position) {
        dialogInterface.dismiss();
        String s_tag = experienceList.get(position).getTag();
        final Experience undoExpr = experienceList.get(position);
        adapter.removeItem(position);
        Call<DefaultResponse> responseCall = RetrofitClient.getInstance().getInterPreter()
                .delExpr(s_id, s_tag);
        responseCall.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.body().isError()) {
                    Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                    adapter.undoItem(undoExpr, position);
                } else {

                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                adapter.undoItem(undoExpr, position);
            }
        });
    }

    @Override
    public void onNegativeClick(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        adapter.editItem();
    }
}
