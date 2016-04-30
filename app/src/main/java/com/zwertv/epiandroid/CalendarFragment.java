package com.zwertv.epiandroid;


import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.zwertv.epiandroid.user.CalendarActivity;
import com.zwertv.epiandroid.user.IUser;
import com.zwertv.epiandroid.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends android.app.Fragment {
    private Long date;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart()
    {
        super.onStart();
        //Info from user
        View view = getView();
        if (view == null)
            Log.d("NULLL", "VIEW NULL");
        //set listener for calendar
        CalendarView cv = (CalendarView) getView().findViewById(R.id.calendardate);
        date = cv.getDate();
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //recup days planning
                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                f.setTimeZone(TimeZone.getDefault());
                try {
                    Date transf = f.parse(year + "/" + (month+1) + "/" + dayOfMonth);
                    Log.d("changed date", dayOfMonth + "/" + (month+1) + "/" + year);

                    view.setDate(transf.getTime());

                    android.app.Fragment fragment = null;
                    fragment = new CalendarPieceFragment();
                    getFragmentManager().beginTransaction().replace(R.id.containerfragcalendar, fragment).commit();
                    Log.d("Listener calendar", Integer.toString(year) + " / " + Integer.toString(month) + " / " + Integer.toString(dayOfMonth));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //recup days planning
        android.app.Fragment fragment = null;
        fragment = new CalendarPieceFragment();
        getFragmentManager().beginTransaction().replace(R.id.containerfragcalendar, fragment).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
}
