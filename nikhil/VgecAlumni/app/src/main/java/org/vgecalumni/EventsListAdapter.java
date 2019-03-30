package org.vgecalumni;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private List<EventItemPovider> itemList;
    private LayoutInflater inflater;

    public EventsListAdapter(Context context, List<EventItemPovider> itemList){
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.event_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(itemList.get(i), i);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout ll_event_item;
        private ImageView iv_event_image;
        private TextView tv_event_name;

        private EventItemPovider item;
        private int postition;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_event_item = itemView.findViewById(R.id.ll_event_item);
            iv_event_image = itemView.findViewById(R.id.iv_event_image);
            tv_event_name = itemView.findViewById(R.id.tv_event_name);

            ll_event_item.setOnClickListener(this);
        }

        void setData(EventItemPovider item, int postition) {

            tv_event_name.setText(item.getName());

            this.item = item;
            this.postition = postition;
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(), QRCodeActivity.class));
        }
    }
}
