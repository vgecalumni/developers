package org.vgecalumni.Components.Events;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.vgecalumni.R;

public class DepartmentalEventsFragment extends Fragment {

    public DepartmentalEventsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_fragment_departmental_events, container, false);

        new DepartmentalEventsProvider().getEventsList(view);

        return view;
    }
}
