package org.vgecalumni.Components.Portfolio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.vgecalumni.R;

public class AlumniPortfolioFragment extends Fragment {

    public AlumniPortfolioFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.portfolio_alumni_fragment, container, false);
        new AlumniPortfolioProvider().getAlumniList(view);
        return view;
    }
}
