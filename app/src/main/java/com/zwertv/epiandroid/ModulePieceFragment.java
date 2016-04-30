package com.zwertv.epiandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModulePieceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModulePieceFragment extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "credits";

    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mCredits;


    public ModulePieceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModulePieceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModulePieceFragment newInstance(String title, String credits) {
        ModulePieceFragment fragment = new ModulePieceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, credits);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM1);
            mCredits = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_piece, container, false);
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
        TextView title = (TextView) view.findViewById(R.id.moduletitle);
        title.setText(mTitle);
        //credits
        TextView content = (TextView) view.findViewById(R.id.credits);
        content.setText(mCredits);
    }
}
