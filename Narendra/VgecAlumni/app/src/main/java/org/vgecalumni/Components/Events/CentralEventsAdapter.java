package org.vgecalumni.Components.Events;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.vgecalumni.Model.CentralEvent;
import org.vgecalumni.R;

import java.util.List;

public class CentralEventsAdapter extends RecyclerView.Adapter<CentralEventsAdapter.ViewHolder> {

    private List<CentralEvent> eventsList;

    public CentralEventsAdapter(List<CentralEvent> eventsList) {
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.events_layout_central_event, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(eventsList.get(i), i);
        viewHolder.setListener();
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cv_event;
        private ImageView iv_event_image;
        private TextView tv_event_name, tv_event_description, tv_event_date;

        private CentralEvent centralEvent;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_event = itemView.findViewById(R.id.cv_central_event);
            iv_event_image = itemView.findViewById(R.id.iv_event_image);
            tv_event_name = itemView.findViewById(R.id.tv_event_name);
            tv_event_description = itemView.findViewById(R.id.tv_event_description);
            tv_event_date = itemView.findViewById(R.id.tv_event_date);
        }

        public void setData(CentralEvent centralEvent, int position) {
            Glide.with(itemView.getContext()).load(centralEvent.getImageURL()).placeholder(R.drawable.event).error(R.drawable.event).transition(DrawableTransitionOptions.withCrossFade()).into(iv_event_image);
            tv_event_name.setText(centralEvent.getName());
            tv_event_description.setText(Html.fromHtml(centralEvent.getDescription()));
            tv_event_date.setText(Html.fromHtml(centralEvent.getDate()));

            this.centralEvent = centralEvent;
            this.position = position;
        }

        public void setListener() {
            cv_event.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();

            if (id == R.id.cv_central_event) {
                Intent intent = new Intent(v.getContext(), EventDetailsActivity.class);
                intent.putExtra("e_id", centralEvent.getId());
                v.getContext().startActivity(intent);
            }
        }
    }
}

