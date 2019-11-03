package org.vgecalumni.Components.Profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.vgecalumni.Model.Education;
import org.vgecalumni.R;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder> {

    List<Education> educationList;
    Context context;

    public EducationAdapter(List<Education> educationList, Context context) {
        this.educationList = educationList;
        this.context = context;
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_education_card, parent, false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        Education education = educationList.get(position);
        holder.degree.setText(education.getDegree());
        holder.stream.setText(education.getStream());
        holder.inst.setText(education.getInst());
        String da = context.getString(R.string.date_from) + education.getJoin() + context.getString(R.string.date_to) + education.getEnd();
        holder.date.setText(da);
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public void removeItem(int position) {
        educationList.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
    }

    public void editItem() {
        notifyDataSetChanged();
    }

    public void undoItem(Education undoEdu, int position) {
        educationList.add(undoEdu);
        notifyItemInserted(position);
    }

    class EducationViewHolder extends RecyclerView.ViewHolder {
        TextView degree, stream, inst, date;

        EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            degree = itemView.findViewById(R.id.edu_degree);
            stream = itemView.findViewById(R.id.edu_stream);
            inst = itemView.findViewById(R.id.edu_inst);
            date = itemView.findViewById(R.id.edu_date);
        }
    }
}
