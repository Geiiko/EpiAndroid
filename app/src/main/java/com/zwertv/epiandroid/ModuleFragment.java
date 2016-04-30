package com.zwertv.epiandroid;


import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zwertv.epiandroid.user.IUser;
import com.zwertv.epiandroid.user.Message;
import com.zwertv.epiandroid.user.Module;
import com.zwertv.epiandroid.user.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModuleFragment extends android.app.Fragment {


    public ModuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        //Info from user
        View view = getView();
        if (view == null)
            Log.d("NULLL", "VIEW NULL");
        //recup modules
        ModuleLoadTask loader = new ModuleLoadTask();
        loader.execute();
    }

    public class ModuleLoadTask extends AsyncTask<Void, Void, List<Module>> {
        public ModuleLoadTask() {
        }

        @Override
        protected List<Module> doInBackground(Void... params) {
            Log.d("Loading image", "Started loading module");
            List<Module> result = null;
            try {
                IUser user = User.getInstance();
                result = user.getModules();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Module> result) {
            super.onPostExecute(result);
            //set all modules
            ModulePieceFragment fragment;
            FragmentTransaction manager = getFragmentManager().beginTransaction();
            for (Module mod : result)
            {
                fragment = ModulePieceFragment.newInstance(mod.getTitle(), Integer.toString(mod.getCredits()));
                manager.add(R.id.modules, fragment);
            }
            manager.commit();
        }
    }

}
