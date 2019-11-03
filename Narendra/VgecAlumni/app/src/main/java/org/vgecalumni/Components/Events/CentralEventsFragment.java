package org.vgecalumni.Components.Events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.vgecalumni.R;

public class CentralEventsFragment extends Fragment {

    public CentralEventsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_fragment_central_events, container, false);

        new CentralEventsProvider().getEventsList(view);

        return view;
    }
}
