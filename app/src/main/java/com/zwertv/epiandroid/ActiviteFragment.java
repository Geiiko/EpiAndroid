package com.zwertv.epiandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextClock;
import android.widget.TextView;

import com.zwertv.epiandroid.user.CalendarActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActiviteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActiviteFragment extends android.app.Fragment {
    private CalendarActivity mToday;


    public ActiviteFragment() {
        // Required empty public constructor
    }

    public void setDays(CalendarActivity today) {this.mToday = today;}

    public static ActiviteFragment newInstance(CalendarActivity param1) {
        ActiviteFragment fragment = new ActiviteFragment();

        fragment.setDays(param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activite, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view = getView();
        if (view == null)
            Log.d("NULLL", "VIEW NULL");
        //set text part
        //title
        TextView title = (TextView) view.findViewById(R.id.activiteTitle);
        title.setText(mToday.getActiTitle());

        TextView time = (TextView) view.findViewById(R.id.activiteTime);
        Date d = CalendarActivity.getTime(mToday, false);
        if (d != null)
            time.setText(d.getHours() +  ":" + ((d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes()));

        TextView timeend = (TextView) view.findViewById(R.id.activiteTimeEnd);
        d = CalendarActivity.getTime(mToday, true);
        if (d != null)
            timeend.setText(d.getHours() +  ":" + ((d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes()));
        //layout.setLayoutParams(new TableRow.LayoutParams(x, y));
        //layout.setLayoutParams(new TableRow.LayoutParams(y));

    }
}
