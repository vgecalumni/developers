package org.vgecalumni.Components.Events;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vgecalumni.R;

import java.util.ArrayList;
import java.util.Iterator;
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

            for(int i = 0; i < departmentalEvent.getEventNameList().size(); i++) {
                eventList.add(new Event(eventIDList.get(i), eventNameList.get(i), eventImageUrlList.get(i)));
            }

            eventsAdapter = new EventsAdapter(eventList);
            rv_events.setAdapter(eventsAdapter);
            rv_events.setItemAnimator(new DefaultItemAnimator());
            rv_events.scheduleLayoutAnimation();
        }
    }
}

class DepartmentalEventsProvider {

    public void getEventsList(final View view) {

        final ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shmr_departmental_events);
        shimmerFrameLayout.startShimmer();

        final String departmentID[] = {
                "dept_chem",
                "dept_civil",
                "dept_ce",
                "dept_ee",
                "dept_ec",
                "dept_it",
                "dept_ic",
                "dept_me",
                "dept_pe"
        };

        String departmentName[] = {
                "Chemical Engineering",
                "Civil Engineering",
                "Computer Engineering",
                "Electrical Engineering",
                "Electronics And Communication Engineering",
                "Information Technology",
                "Instrumentation And Control Engineering",
                "Mechanical Engineering",
                "Power Electronics"
        };

        final List<DepartmentalEvent> departmentalEventList = new ArrayList<>();
        for(int i = 0; i < departmentID.length; i++) {
            departmentalEventList.add(new DepartmentalEvent(departmentName[i]));
        }

        String url = "https://admin.vgecalumni.org/android/get_main_events.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject item = array.getJSONObject(i);
                                String eventType = item.getString("e_type");

                                if(!eventType.equals("null")) {
                                    if(!eventType.equals("central")) {
                                        for (int j = 0; j < departmentID.length; j++) {
                                            if(eventType.equals(departmentID[j])) {
                                                String id = item.getString("e_id");
                                                String name = item.getString("e_name");
                                                String imageURL = item.getString("e_banner");

                                                if(!id.equals("null") || !name.equals("null")) {
                                                    DepartmentalEvent departmentalEvent = departmentalEventList.get(j);
                                                    departmentalEvent.addEvent(id, name, imageURL);
                                                    departmentalEventList.set(j, departmentalEvent);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            Iterator<DepartmentalEvent> iterator = departmentalEventList.iterator();
                            while (iterator.hasNext()) {
                                DepartmentalEvent departmentalEvent = iterator.next();
                                if (departmentalEvent.getEventIDList().size() == 0) {
                                    iterator.remove();
                                }
                            }

                            Activity activity = (Activity) view.getContext();
                            if(!activity.isDestroyed()) {
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);

                                RecyclerView rv_departmental_events = view.findViewById(R.id.rv_departmental_events);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                rv_departmental_events.setLayoutManager(layoutManager);

                                DepartmentalEventsAdapter departmentalEventsAdapter = new DepartmentalEventsAdapter(departmentalEventList);
                                rv_departmental_events.setAdapter(departmentalEventsAdapter);
                                rv_departmental_events.setItemAnimator(new DefaultItemAnimator());
                                rv_departmental_events.scheduleLayoutAnimation();
                            }
                        } catch (JSONException e) {

                            new AlertDialog.Builder(view.getContext())
                                    .setTitle("No Internet Connection")
                                    .setMessage("Please check your internet connectivity and try again")
                                    .setCancelable(false)
                                    .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            getEventsList(view);
                                        }
                                    })
                                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            Activity activity = (Activity) view.getContext();
                                            activity.finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        new AlertDialog.Builder(view.getContext())
                                .setTitle("No Internet Connection")
                                .setMessage("Please check your internet connectivity and try again")
                                .setCancelable(false)
                                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        getEventsList(view);
                                    }
                                })
                                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        Activity activity = (Activity) view.getContext();
                                        activity.finish();
                                    }
                                })
                                .show();
                    }
                });

        Volley.newRequestQueue(view.getContext()).add(stringRequest);

    }
}

class DepartmentalEvent {

    private String name;
    private List<String> eventIDList;
    private List<String> eventNameList;
    private List<String> eventImageUrlList;

    public DepartmentalEvent(String name) {
        this.name = name;
        eventIDList = new ArrayList<>();
        eventNameList = new ArrayList<>();
        eventImageUrlList = new ArrayList<>();
    }

    public void addEvent(String id, String name, String imageURL) {
        eventIDList.add(id);
        eventNameList.add(name);
        eventImageUrlList.add(imageURL);
    }

    public String getName() {
        return name;
    }

    public List<String> getEventIDList() {
        return eventIDList;
    }

    public List<String> getEventNameList() {
        return eventNameList;
    }

    public List<String> getEventImageUrlList() {
        return eventImageUrlList;
    }
}
