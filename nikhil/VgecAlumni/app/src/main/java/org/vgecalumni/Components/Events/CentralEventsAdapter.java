package org.vgecalumni.Components.Events;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vgecalumni.R;
import org.w3c.dom.Text;

import java.util.ArrayList;
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

            if(id == R.id.cv_central_event) {
                Intent intent = new Intent(v.getContext(), EventDetailsActivity.class);
                intent.putExtra("e_id", centralEvent.getId());
                v.getContext().startActivity(intent);
            }
        }
    }
}

class CentralEventsProvider {

    public void getEventsList(final View view) {

        final ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shmr_central_events);
        shimmerFrameLayout.startShimmer();

        final List<CentralEvent> centralList = new ArrayList<>();

        final String name[] = {
                "Event 1",
                "Event 2",
                "Event 3",
                "Event 4",
                "Event 5"
        };

        String desc[] = {
                "<font color='#000'><b>Description : </b></font>asgjkebgkas adgawgsgsrhrah rbwkg bewkavub swuvbaubuw abbusdh sahilewh gihelw iahgladi hslvneavbd slaivsel ihildslki alsdih",
                "<font color='#000'><b>Description : </b></font>asgjkebgkasadgawgsgsrhrahrbwkgbewkavubswuvbaubuwabbusdhsahilewhgihelwiahgladihslvneavbdslaivselihildslkialsdih",
                "<font color='#000'><b>Description : </b></font>asgjkebgkasadgawgsgsrhrahrbwkgbewkavubswuvbaubuwabbusdhsahilewhgihelwiahgladihslvneavbdslaivselihildslkialsdih",
                "<font color='#000'><b>Description : </b></font>asgjkebgkasadgawgsgsrhrahrbwkgbewkavubswuvbaubuwabbusdhsahilewhgihelwiahgladihslvneavbdslaivselihildslkialsdih",
                "<font color='#000'><b>Description : </b></font>asgjkebgkasadgawgsgsrhrahrbwkgbewkavubswuvbaubuwabbusdhsahilewhgihelwiahgladihslvneavbdslaivselihildslkialsdih"
        };

        final String imageURL[] = {
                "https://www.w3schools.com/w3css/img_lights.jpg",
                "https://www.w3schools.com/w3css/img_lights.jpg",
                "https://www.w3schools.com/w3css/img_lights.jpg",
                "https://www.w3schools.com/w3css/img_lights.jpg",
                "https://www.w3schools.com/w3css/img_lights.jpg"
        };

        final String date[] = {
                "<font color='#000'><b>Event Date : </b></font>01/06/2019",
                "<font color='#000'><b>Event Date : </b></font>01/06/2019",
                "<font color='#000'><b>Event Date : </b></font>01/06/2019",
                "<font color='#000'><b>Event Date : </b></font>01/06/2019",
                "<font color='#000'><b>Event Date : </b></font>01/06/2019"
        };

        String url = "https://admin.vgecalumni.org/android/get_main_events.php";

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject item = array.getJSONObject(i);
                                String eventType = item.getString("e_type");

                                if(!eventType.equals("null")) {
                                    if(eventType.equals("central")) {
                                        String id = item.getString("e_id");
                                        String name = item.getString("e_name");
                                        String imageURL = item.getString("e_banner");
                                        String t_desc = item.getString("e_abstract");
                                        String t_date = item.getString("e_date");

                                        if(t_desc.equals("null")) {
                                            t_desc = "N/A";
                                        }
                                        if(t_date.equals("null")) {
                                            t_date = "N/A";
                                        }

                                        String description = "<font color='#000'><b>Description : </b></font>" + t_desc;
                                        String date = "<font color='#000'><b>Event Date : </b></font>" + t_date;

                                        if(!id.equals("null") || !name.equals("null")) {
                                            centralList.add(new CentralEvent(id, name, imageURL, description, date));
                                        }
                                    }
                                }
                            }

                            Activity activity = (Activity) view.getContext();
                            if(!activity.isDestroyed()) {
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);

                                RecyclerView rv_central_events = view.findViewById(R.id.rv_central_events);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                rv_central_events.setLayoutManager(layoutManager);

                                CentralEventsAdapter centralEventsAdapter = new CentralEventsAdapter(centralList);
                                rv_central_events.setAdapter(centralEventsAdapter);
                                rv_central_events.setItemAnimator(new DefaultItemAnimator());
                                rv_central_events.scheduleLayoutAnimation();
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

class CentralEvent {

    private String id, name, imageURL, description, date;

    public CentralEvent(String id, String name, String imageURL, String description, String date) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
