package org.vgecalumni.Components.Events;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vgecalumni.Model.EventDetails;
import org.vgecalumni.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDetailsActivity extends AppCompatActivity {

    private AppBarLayout app_bar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private Toolbar toolbar;
    private ImageView iv_event_image;
    private TextView tv_date, tv_fees;
    private Button bt_register;

    private String title = " ";
    private String r_url = " ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_activity_event_details);

        initialize();
        setListener();
        setData(this);
    }

    private void initialize() {
        app_bar = findViewById(R.id.app_bar);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolbar);
        iv_event_image = findViewById(R.id.iv_event_image);
        tv_date = findViewById(R.id.tv_event_date);
        tv_fees = findViewById(R.id.tv_event_fees);
        bt_register = findViewById(R.id.bt_register);

        collapsing_toolbar.setTitle(" ");
    }

    private void setListener() {
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (Math.abs(i) - appBarLayout.getTotalScrollRange() == 0) {
                    collapsing_toolbar.setTitle(title);
                } else {
                    collapsing_toolbar.setTitle(" ");
                }
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(r_url)));
            }
        });
    }

    private void setData(final Activity activity) {
        final ShimmerFrameLayout shimmerFrameLayout = activity.findViewById(R.id.shmr_events_details);
        shimmerFrameLayout.startShimmer();

        final String e_id = getIntent().getExtras().getString("e_id");

        final List<EventDetails> eventDetailsList = new ArrayList<>();

        final String url = "https://admin.vgecalumni.org/android/get_main_events.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject item = array.getJSONObject(i);

                                String name = item.getString("e_name");
                                String description = item.getString("e_abstract");
                                String imageURL = item.getString("e_banner");
                                String outcome = item.getString("e_outcome");
                                String goal = item.getString("e_goal");
                                String detail = item.getString("e_detail");
                                String fees = item.getString("e_fees");
                                String date = item.getString("e_date");
                                String register_url = item.getString("e_url");

                                if (!activity.isDestroyed()) {
                                    title = name;
                                    Glide.with(EventDetailsActivity.this).load(imageURL).placeholder(R.drawable.event).error(R.drawable.event).into(iv_event_image);

                                    if (!date.equals("null")) {
                                        String string = "Date : " + date;
                                        tv_date.setText(string);
                                    } else {
                                        tv_date.setVisibility(View.GONE);
                                    }

                                    if (!fees.equals("null")) {
                                        String string = "Fees : ₹" + fees;
                                        tv_fees.setText(string);
                                    } else {
                                        tv_fees.setVisibility(View.GONE);
                                    }

                                    if (!register_url.equals("null")) {
                                        r_url = register_url;
                                    }

                                    if (!description.equals("null")) {
                                        eventDetailsList.add(new EventDetails("Description", description));
                                    }

                                    if (!outcome.equals("null")) {
                                        eventDetailsList.add(new EventDetails("Outcome", outcome));
                                    }

                                    if (!goal.equals("null")) {
                                        eventDetailsList.add(new EventDetails("Goal", goal));
                                    }

                                    if (!detail.equals("null")) {
                                        eventDetailsList.add(new EventDetails("Details", detail));
                                    }
                                }
                            }

                            if (!activity.isDestroyed()) {
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);

                                RecyclerView rv_event_details = findViewById(R.id.rv_event_details);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(EventDetailsActivity.this);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                rv_event_details.setLayoutManager(layoutManager);

                                EventDetailsAdapter eventDetailsAdapter = new EventDetailsAdapter(eventDetailsList);
                                rv_event_details.setAdapter(eventDetailsAdapter);
                                rv_event_details.setItemAnimator(new DefaultItemAnimator());
                                rv_event_details.scheduleLayoutAnimation();
                            }
                        } catch (JSONException e) {

                            new AlertDialog.Builder(EventDetailsActivity.this)
                                    .setTitle("No Internet Connection")
                                    .setMessage("Please check your internet connectivity and try again")
                                    .setCancelable(false)
                                    .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            setData(activity);
                                        }
                                    })
                                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            EventDetailsActivity.this.finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        new AlertDialog.Builder(EventDetailsActivity.this)
                                .setTitle("No Internet Connection")
                                .setMessage("Please check your internet connectivity and try again")
                                .setCancelable(false)
                                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        setData(activity);
                                    }
                                })
                                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        EventDetailsActivity.this.finish();
                                    }
                                })
                                .show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("e_id", e_id);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);

    }
}
