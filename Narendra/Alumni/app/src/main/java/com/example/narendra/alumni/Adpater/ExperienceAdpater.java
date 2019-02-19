package com.example.narendra.alumni.Adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narendra.alumni.Model.Education;
import com.example.narendra.alumni.Model.Experience;
import com.example.narendra.alumni.R;

import java.util.List;

public class ExperienceAdpater extends RecyclerView.Adapter<ExperienceAdpater.ExperienceViewHolder> {
    List<Experience> experienceList;
    Context context;

    public ExperienceAdpater(List<Experience> experienceList, Context context) {
        this.experienceList = experienceList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExperienceAdpater.ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.experiance_card,parent,false);
        return new ExperienceAdpater.ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceAdpater.ExperienceViewHolder holder, int position) {
        Experience experience= experienceList.get(position);
        holder.comp.setText(experience.getCompany());
        holder.desig.setText(experience.getDesig());
        holder.desc.setText(experience.getDesc());
        String da=context.getString(R.string.date_from)+experience.getJoin()+context.getString(R.string.date_to)+experience.getEnd();
        holder.date.setText(da);
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    class ExperienceViewHolder extends RecyclerView.ViewHolder {
        TextView comp, desig, desc, date;
        ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            comp=itemView.findViewById(R.id.expr_company);
            desig=itemView.findViewById(R.id.expr_designation);
            desc=itemView.findViewById(R.id.expr_desc);
            date=itemView.findViewById(R.id.expr_date);
        }
    }
}
