package com.example.narendra.alumni.Adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narendra.alumni.Model.Education;
import com.example.narendra.alumni.R;

import java.util.List;

public class EducationAdpater extends RecyclerView.Adapter<EducationAdpater.EducationViewHolder> {

    List<Education> educationList;
    Context context;

    public EducationAdpater(List<Education> educationList, Context context) {
        this.educationList = educationList;
        this.context = context;
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.education_card,parent,false);
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

    class EducationViewHolder extends RecyclerView.ViewHolder {
        TextView degree, stream, inst, date;
        EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            degree=itemView.findViewById(R.id.edu_degree);
            stream=itemView.findViewById(R.id.edu_stream);
            inst=itemView.findViewById(R.id.edu_inst);
            date=itemView.findViewById(R.id.edu_date);
        }
    }
}
