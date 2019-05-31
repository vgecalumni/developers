package org.vgecalumni.Adpater;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.vgecalumni.Components.Profile.Add_Experience;
import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Components.Profile.Education_List;
import org.vgecalumni.Model.DefaultResponse;
import org.vgecalumni.Model.Experience;
import org.vgecalumni.Model.SharedUser;
import org.vgecalumni.Model.User;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExperienceListAdpter extends RecyclerView.Adapter<ExperienceListAdpter.ExperienceViewHolder> {

    List<Experience> experienceList;
    Context context;

    public ExperienceListAdpter(List<Experience> experienceList, Context context) {
        this.experienceList = experienceList ;
        this.context = context;
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.profile_experience_list_card,parent,false);
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Experience experience= experienceList.get(position);
        holder.company.setText(experience.getCompany());
        holder.design.setText(experience.getDesig());
        holder.desc.setText(experience.getDesc());
        String da=context.getString(R.string.date_from)+experience.getJoin()+context.getString(R.string.date_to)+experience.getEnd();
        holder.date.setText(da);
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    class ExperienceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView company, design, desc, date;
        ImageButton edit,del;
        ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            company=itemView.findViewById(R.id.expr_company);
            design=itemView.findViewById(R.id.expr_designation);
            desc=itemView.findViewById(R.id.expr_desc);
            date=itemView.findViewById(R.id.expr_date);
            edit=itemView.findViewById(R.id.expr_edit);
            edit.setOnClickListener(this);
            del=itemView.findViewById(R.id.expr_delete);
            del.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i=getAdapterPosition();
            Experience experience1=experienceList.get(i);
            User user=SharedPrefManager.getmInstance(context).getUser();
            switch (v.getId()){
                case R.id.expr_edit:
                    Intent intent = new Intent(context, Add_Experience.class);
                    intent.putExtra("for",1);
                    intent.putExtra("company",experience1.getCompany());
                    intent.putExtra("desig",experience1.getDesig());
                    intent.putExtra("desc",experience1.getDesc());
                    intent.putExtra("join",experience1.getJoin());
                    intent.putExtra("end",experience1.getEnd());
                    intent.putExtra("tag",experience1.getTag());
                    context.startActivity(intent);
                    break;

                case R.id.expr_delete:
                    Call<DefaultResponse> responseCall = RetrofitClient.getInstance().getInterPreter()
                            .delExpr(user.getId(),experience1.getTag());
                    responseCall.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            if(response.body().isError()){
                                //
                            }else {
                                ((Education_List)context).getEducation();
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {
                            //
                        }
                    });
                    break;
            }
        }
    }
}
