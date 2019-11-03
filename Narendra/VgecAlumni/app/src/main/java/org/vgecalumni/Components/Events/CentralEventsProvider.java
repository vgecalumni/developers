package org.vgecalumni.Components.Events;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vgecalumni.Model.CentralEvent;
import org.vgecalumni.R;

import java.util.ArrayList;
import java.util.List;

class CentralEventsProvider {

    public void getEventsList(final View view) {

        final ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shmr_central_events);
        shimmerFrameLayout.startShimmer();

        final List<CentralEvent> centralList = new ArrayList<>();

        final String[] name = {
                "Event 1",
                "Event 2",
                "Event 3",
                "Event 4",
                "Event 5"
        };

        String[] desc = {
                "<font color='#000'><b>Description : </b></font>asgjkebgkas adgawgsgsrhrah rbwkg bewkavub swuvbaubuw abbusdh sahilewh gihelw iahgladi hslvneavbd slaivsel ihildslki alsdih",
                "<font color='#000'><b>Description : </b></font>asgjkebgkasadgawgsgsrhrahrbwkgbewkavubswuvbaubuwabbusdhsahilewhgihelwiahgladihslvneavbdslaivselihildslkialsdih",
                "<font color='#000'><b>Description : </b></font>asgjkebgkasadgawgsgsrhrahrbwkgbewkavubswuvbaubuwabbusdhsahilewhgihelwiahgladihslvneavbdslaivselihildslkialsdih",
                "<font color='#000'><b>Description : </b></font>asgjkebgkasadgawgsgsrhrahrbwkgbewkavubswuvbaubuwabbusdhsahilewhgihelwiahgladihslvneavbdslaivselihildslkialsdih",
                "<font color='#000'><b>Description : </b></font>asgjkebgkasadgawgsgsrhrahrbwkgbewkavubswuvbaubuwabbusdhsahilewhgihelwiahgladihslvneavbdslaivselihildslkialsdih"
        };

        final String[] imageURL = {
                "https://www.w3schools.com/w3css/img_lights.jpg",
                "https://www.w3schools.com/w3css/img_lights.jpg",
                "https://www.w3schools.com/w3css/img_lights.jpg",
                "https://www.w3schools.com/w3css/img_lights.jpg",
                "https://www.w3schools.com/w3css/img_lights.jpg"
        };

        final String[] date = {
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

                                if (!eventType.equals("null")) {
                                    if (eventType.equals("central")) {
                                        String id = item.getString("e_id");
                                        String name = item.getString("e_name");
                                        String imageURL = item.getString("e_banner");
                                        String t_desc = item.getString("e_abstract");
                                        String t_date = item.getString("e_date");

                                        if (t_desc.equals("null")) {
                                            t_desc = "N/A";
                                        }
                                        if (t_date.equals("null")) {
                                            t_date = "N/A";
                                        }

                                        String description = "<font color='#000'><b>Description : </b></font>" + t_desc;
                                        String date = "<font color='#000'><b>Event Date : </b></font>" + t_date;

                                        if (!id.equals("null") || !name.equals("null")) {
                                            centralList.add(new CentralEvent(id, name, imageURL, description, date));
                                        }
                                    }
                                }
                            }

                            Activity activity = (Activity) view.getContext();
                            if (!activity.isDestroyed()) {
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
