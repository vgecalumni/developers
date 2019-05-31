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

import org.vgecalumni.Components.Profile.Add_Education;
import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Components.Profile.Education_List;
import org.vgecalumni.Model.DefaultResponse;
import org.vgecalumni.Model.Education;
import org.vgecalumni.Model.SharedUser;
import org.vgecalumni.Model.User;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationListAdpter extends RecyclerView.Adapter<EducationListAdpter.EducationViewHolder> {

    List<Education> educationList;
    Context context;

    public EducationListAdpter(List<Education> educationList, Context context) {
        this.educationList = educationList;
        this.context = context;
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.profile_education_list_card,parent,false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        Education education= educationList.get(position);
        holder.degree.setText(education.getDegree());
        holder.stream.setText(education.getStream());
        holder.inst.setText(education.getInst());
        String da=context.getString(R.string.date_from)+education.getJoin()+context.getString(R.string.date_to)+education.getEnd();
        holder.date.setText(da);
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    class EducationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView degree, stream, inst, date;
        ImageButton edit,del;
        EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            degree=itemView.findViewById(R.id.edu_degree);
            stream=itemView.findViewById(R.id.edu_stream);
            inst=itemView.findViewById(R.id.edu_inst);
            date=itemView.findViewById(R.id.edu_date);
            edit=itemView.findViewById(R.id.edu_edit);
            edit.setOnClickListener(this);
            del=itemView.findViewById(R.id.edu_delete);
            del.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i=getAdapterPosition();
            Education education1=educationList.get(i);
            User user=SharedPrefManager.getmInstance(context).getUser();
            switch (v.getId()){
                case R.id.edu_edit:
                    Intent intent = new Intent(context, Add_Education.class);
                    intent.putExtra("for",1);
                    intent.putExtra("degree",education1.getDegree());
                    intent.putExtra("stream",education1.getStream());
                    intent.putExtra("inst",education1.getInst());
                    intent.putExtra("join",education1.getJoin());
                    intent.putExtra("end",education1.getEnd());
                    intent.putExtra("tag",education1.getTag());
                    context.startActivity(intent);
                    break;

                case R.id.edu_delete:
                    Call<DefaultResponse> responseCall = RetrofitClient.getInstance().getInterPreter()
                            .delEdu(user.getId(),education1.getTag());
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
