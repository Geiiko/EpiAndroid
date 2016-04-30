package com.zwertv.epiandroid;


import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.TextView;

import com.zwertv.epiandroid.user.CalendarActivity;
import com.zwertv.epiandroid.user.IUser;
import com.zwertv.epiandroid.user.Module;
import com.zwertv.epiandroid.user.User;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarPieceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarPieceFragment extends android.app.Fragment {
    private List<CalendarActivity> mListOfAct;
    private ActLoadTask loader;

    public CalendarPieceFragment() {
        // Required empty public constructor
    }

    public void setmListOfAct(List<CalendarActivity> arg) {mListOfAct = arg;}

    public static CalendarPieceFragment newInstance() {
        CalendarPieceFragment fragment = new CalendarPieceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        try {
            super.onStart();
            //recup calendarview
            CalendarView calendarview = (CalendarView) getActivity().findViewById(R.id.calendardate);
            //recup list act
            loader = new ActLoadTask(calendarview.getDate());
            loader.execute();
        } catch (Exception e)
        {}
    }

    public void onDestroy() {
        Log.d("destry piece", "destroy piece");
        if (loader != null)
            loader.cancel(true);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar_piece, container, false);
    }

    public class ActLoadTask extends AsyncTask<Void, Void, List<CalendarActivity>> {
        private long mDate;
        public ActLoadTask(long date) {
            mDate = date;
        }

        @Override
        protected List<CalendarActivity> doInBackground(Void... params) {
            Log.d("Loading calendar", "Started loading calendar");
            List<CalendarActivity> result = null;
            try {
                IUser user = User.getInstance();
                Log.d("Value of date", String.valueOf(mDate));
                Date t = new Date(mDate);
                Log.d("date", t.getDay() + "/" + t.getMonth() + "/" + t.getYear());
                result = user.getActivitiesOfDay(new Date(mDate));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<CalendarActivity> result) {
            super.onPostExecute(result);
            //set all modules
            ActiviteFragment fragment;
            FragmentTransaction manager = getChildFragmentManager().beginTransaction();
            if (result != null) {
                List<CalendarActivity> alreadyadded = new ArrayList<CalendarActivity>();
                for (CalendarActivity act : result) {
                    if (isCancelled())
                        break;
                    Log.d("Iteration calendar", act.getActiTitle());
                    fragment = ActiviteFragment.newInstance(act);
                    // ToDo: Order by start date and end date
                    manager.add(R.id.daylayout, fragment);
                }
                manager.commit();
            }
        }
    }
}
