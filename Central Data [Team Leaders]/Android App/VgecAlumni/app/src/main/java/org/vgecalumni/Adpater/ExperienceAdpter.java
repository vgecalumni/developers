package org.vgecalumni.Adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.vgecalumni.Model.Education;
import org.vgecalumni.Model.Experience;
import org.vgecalumni.R;

import java.util.List;

public class ExperienceAdpter extends RecyclerView.Adapter<ExperienceAdpter.ExperienceViewHolder> {
    List<Experience> experienceList;
    Context context;

    public ExperienceAdpter(List<Experience> experienceList, Context context) {
        this.experienceList = experienceList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExperienceAdpter.ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.profile_experiance_card,parent,false);
        return new ExperienceAdpter.ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceAdpter.ExperienceViewHolder holder, int position) {
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

    public void removeItem(int position) {
        experienceList.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
    }

    public void editItem() {
        notifyDataSetChanged();
    }

    public void undoItem(Experience undoExpr, int position) {
        experienceList.add(undoExpr);
        notifyItemInserted(position);
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
