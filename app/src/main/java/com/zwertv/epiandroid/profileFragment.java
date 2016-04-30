package com.zwertv.epiandroid;


import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zwertv.epiandroid.user.IUser;
import com.zwertv.epiandroid.user.Message;
import com.zwertv.epiandroid.user.Student;
import com.zwertv.epiandroid.user.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class profileFragment extends android.app.Fragment {


    public profileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        //Info from user
        View view = getView();
        if (view == null)
            Log.d("NULLL", "VIEW NULL");
        //recup image profile
        ImageView i = (ImageView) view.findViewById(R.id.ProfilePicture);
        ImageLoadTask loader = new ImageLoadTask(i);
        loader.execute();
        //recup login
        TextView t = (TextView) view.findViewById(R.id.login);
        t.setText(User.getInstance().getLogin());
        //recup temp de log
        InfoLoadTask infloader = new InfoLoadTask();
        infloader.execute();
        //recup messages
        MessageLoadTask msgloader = new MessageLoadTask();
        msgloader.execute();
        super.onStart();
    }

    public class MessageLoadTask extends AsyncTask<Void, Void, List<Message>> {
        public MessageLoadTask() {
        }

        @Override
        protected List<Message> doInBackground(Void... params) {
            Log.d("Loading message", "Started loading messages");
            try {
                IUser user = User.getInstance();
                List<Message> myList = user.getMessages();
                return myList;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Message> result) {
            super.onPostExecute(result);
            //set all messages
            MessageFragment fragment;
            FragmentTransaction manager = getFragmentManager().beginTransaction();
            for (Message msg : result)
            {
                Log.d("iteration", msg.getTitle());
                fragment = MessageFragment.newInstance(msg.getTitle(), msg.getContent(), msg.getUserPicture());
                //layoutView.addView(fragment);
                manager.add(R.id.messages, fragment);
            }
            manager.commit();
        }
    }

    public class InfoLoadTask extends AsyncTask<Void, Void, Student> {
        public InfoLoadTask() {
        }

        @Override
        protected Student doInBackground(Void... params) {
            Log.d("Loading info", "Started loading info");
            try {
                IUser user = User.getInstance();
                Student mystudent = user.getStudent(user.getLogin());
                return mystudent;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Student result) {
            super.onPostExecute(result);
            if (result != null) {
                TextView t;
                t = (TextView) getView().findViewById(R.id.gpa);
                t.setText("GPA : " + result.getGpa()[0]);
                t = (TextView) getView().findViewById(R.id.lognorm);
                t.setText("Temps de log actif : " + Integer.toString(result.getNetSoulStat().getActive()) + "h");
                PercentView p = (PercentView) getView().findViewById(R.id.percentview);
                p.percentage = result.getNetSoulStat().getActive();
                p.percentageoff = result.getNetSoulStat().getLogNorm();
                p.forceLayout();
            }
        }
    }
}
