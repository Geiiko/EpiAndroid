package com.zwertv.epiandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwertv.epiandroid.user.IUser;
import com.zwertv.epiandroid.user.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "content";
    private static final String ARG_PARAM3 = "imgurl";

    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mContent;
    private String mImgUrl;

    public MessageFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String title, String content, String imgurl) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, content);
        args.putString(ARG_PARAM3, imgurl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM1);
            mContent = getArguments().getString(ARG_PARAM2);
            mImgUrl = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
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
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(Html.fromHtml(mTitle));
        //content
        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(Html.fromHtml(mContent));
        //set image
        ImageView i = (ImageView) view.findViewById(R.id.imagemessage);
        ImageLoadTaskMess loader = new ImageLoadTaskMess(i);
        loader.execute();
    }

    public class ImageLoadTaskMess extends AsyncTask<Void, Void, Bitmap> {

        private ImageView imageView;

        public ImageLoadTaskMess(ImageView imageView) {this.imageView = imageView;}

        @Override
        protected Bitmap doInBackground(Void... params) {
            Log.d("Loading image", "Started loading image for message");
            try {
                URL urlConnection = new URL(mImgUrl);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }
    }
}
