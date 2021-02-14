package com.picsapp.moatazbadawy.pojo4k.Fragment.SettingFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.picsapp.moatazbadawy.pojo4k.Activity.MainActivity;
import com.picsapp.moatazbadawy.pojo4k.R;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class AboutFragment extends Fragment {

    private static final int MAGICAL_NUMBER = 0;
    static int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        Toolbar();
        // Inflate the layout for this fragment

        RelativeLayout button2 = rootView.findViewById(R.id.app_version);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.normal(Objects.requireNonNull(getActivity()), "version 1.0.1", Toast.LENGTH_LONG).show();
            }
        });

        RelativeLayout button = rootView.findViewById(R.id.dev1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/thecoderui/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return rootView;
    }

    private void Toolbar () {
        Objects.requireNonNull(getActivity()).setTitle("About");
    }

    private void restartApp() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        int mPendingIntentId = MAGICAL_NUMBER;
        PendingIntent mPendingIntent = PendingIntent.getActivity(getActivity(), mPendingIntentId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }
}