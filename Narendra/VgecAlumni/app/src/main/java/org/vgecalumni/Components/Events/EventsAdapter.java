package org.vgecalumni.Components.Events;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.vgecalumni.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

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
        viewHolder.performAnimation();
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cv_event;
        private ImageView iv_event_image;
        private TextView tv_event_name;

        private ScaleAnimation anim;

        private Event event;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_event = itemView.findViewById(R.id.cv_event);
            iv_event_image = itemView.findViewById(R.id.iv_event_image);
            tv_event_name = itemView.findViewById(R.id.tv_event_name);

            anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(300);
        }

        public void setData(Event event, int position) {
            Glide.with(itemView.getContext()).load(event.getImageUrl()).placeholder(R.drawable.event).error(R.drawable.event).into(iv_event_image);
            tv_event_name.setText(event.getName());

            this.event = event;
            this.position = position;
        }

        public void setListener() {
            cv_event.setOnClickListener(this);
        }

        public void performAnimation() {
            this.itemView.startAnimation(anim);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();

            if(id == R.id.cv_event) {
                Intent intent = new Intent(v.getContext(), EventDetailsActivity.class);
                intent.putExtra("e_id", event.getId());
                v.getContext().startActivity(intent);
            }
        }
    }
}

class Event {

    private String id, name, imageUrl;

    public Event(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
