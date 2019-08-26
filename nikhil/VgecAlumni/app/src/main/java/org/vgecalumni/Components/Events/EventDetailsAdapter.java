package org.vgecalumni.Components.Events;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.vgecalumni.R;

import java.util.List;

public class EventDetailsAdapter extends RecyclerView.Adapter<EventDetailsAdapter.ViewHolder> {

    List<EventDetails> eventDetailsList;

    public EventDetailsAdapter(List<EventDetails> eventDetailsList) {
        this.eventDetailsList = eventDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.events_layout_event_details, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(eventDetailsList.get(i));
    }

    @Override
    public int getItemCount() {
        return eventDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
        }

        public void setData(EventDetails eventDetails) {
            tv_title.setText(eventDetails.getTitle());
            tv_description.setText(eventDetails.getDescription());
        }
    }
}

class EventDetails {

    private String title, description;

    public EventDetails(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
