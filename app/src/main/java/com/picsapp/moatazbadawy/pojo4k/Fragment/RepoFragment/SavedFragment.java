package com.picsapp.moatazbadawy.pojo4k.Fragment.RepoFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.picsapp.moatazbadawy.pojo4k.R;

import java.util.Objects;


public class SavedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toolbar();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saves, container, false);
    }

    private void Toolbar () {
        Objects.requireNonNull(getActivity()).setTitle("Saved");
    }
}