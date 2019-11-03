package org.vgecalumni.Components.Events;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.vgecalumni.Model.DepartmentalEvent;
import org.vgecalumni.Model.Event;
import org.vgecalumni.R;

import java.util.ArrayList;
import java.util.List;

public class DepartmentalEventsAdapter extends RecyclerView.Adapter<DepartmentalEventsAdapter.ViewHolder> {

    private List<DepartmentalEvent> eventsList;

    public DepartmentalEventsAdapter(List<DepartmentalEvent> eventsList) {
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.events_layout_departmental_event, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(eventsList.get(i), i);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        private TextView tv_department_name;
        private RecyclerView rv_events;
        private EventsAdapter eventsAdapter;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            tv_department_name = itemView.findViewById(R.id.tv_department_name);

            rv_events = itemView.findViewById(R.id.rv_events);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_events.setLayoutManager(layoutManager);
        }

        public void setData(DepartmentalEvent departmentalEvent, int position) {
            tv_department_name.setText(departmentalEvent.getName());

            List<Event> eventList = new ArrayList<>();
            List<String> eventIDList = departmentalEvent.getEventIDList();
            List<String> eventNameList = departmentalEvent.getEventNameList();
            List<String> eventImageUrlList = departmentalEvent.getEventImageUrlList();

            for (int i = 0; i < departmentalEvent.getEventNameList().size(); i++) {
                eventList.add(new Event(eventIDList.get(i), eventNameList.get(i), eventImageUrlList.get(i)));
            }

            eventsAdapter = new EventsAdapter(eventList);
            rv_events.setAdapter(eventsAdapter);
            rv_events.setItemAnimator(new DefaultItemAnimator());
            rv_events.scheduleLayoutAnimation();
        }
    }
}

