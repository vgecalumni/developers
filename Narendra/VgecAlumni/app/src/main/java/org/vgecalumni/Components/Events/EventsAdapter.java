package org.vgecalumni.Components.Events;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.vgecalumni.Model.Event;
import org.vgecalumni.R;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<Event> eventsList;

    public EventsAdapter(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.events_layout_event, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.setData(eventsList.get(i), i);
        viewHolder.setListener();
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cv_event;
        private ImageView iv_event_image;
        private TextView tv_event_name;

        private Event event;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_event = itemView.findViewById(R.id.cv_event);
            iv_event_image = itemView.findViewById(R.id.iv_event_image);
            tv_event_name = itemView.findViewById(R.id.tv_event_name);

//            anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            anim.setDuration(300);
        }

        public void setData(Event event, int position) {
            Glide.with(itemView.getContext()).load(event.getImageUrl()).placeholder(R.drawable.event).error(R.drawable.event).transition(DrawableTransitionOptions.withCrossFade()).into(iv_event_image);
            tv_event_name.setText(event.getName());

            this.event = event;
            this.position = position;
        }

        public void setListener() {
            cv_event.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();

            if (id == R.id.cv_event) {
                Intent intent = new Intent(v.getContext(), EventDetailsActivity.class);
                intent.putExtra("e_id", event.getId());
                v.getContext().startActivity(intent);
            }
        }
    }
}

