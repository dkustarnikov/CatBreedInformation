package com.mitya.catbreedinformation;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mitya.catbreedinformation.helpers.DownloadImageTask;

import java.io.InputStream;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatInformationFragment extends Fragment {

    private TextView nameTextView;
    private TextView temperamentTextView;
    private TextView originTextView;
    private ImageView catImageView;

    private TextView nameTitleTextView;
    private TextView temperamentTitleTextView;
    private TextView originTitleTextView;

    Drawable image;

    public CatInformationFragment() {
        // Required empty public constructor
    }


    public static CatInformationFragment newInstance(String name, String origin, String temperament) {
        CatInformationFragment fragment = new CatInformationFragment();
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
        View v = inflater.inflate(R.layout.fragment_cat_information, container, false);

        nameTextView = v.findViewById(R.id.nameTextView);
        originTextView = v.findViewById(R.id.originTextView);
        temperamentTextView = v.findViewById(R.id.temperamentTextView);
        nameTitleTextView = v.findViewById(R.id.nameTitleTextView);
        originTitleTextView = v.findViewById(R.id.originTitleTextView);
        temperamentTitleTextView = v.findViewById(R.id.temperamentTitleTextView);

        catImageView = v.findViewById(R.id.catImageView);

        return v;
    }

    // This is a public method that the Activity can use to communicate
    // directly with this Fragment
    public void youveGotMail(String name, String origin, String temperament, String imageURL) {
        nameTextView.setText(name);
        originTextView.setText(origin);
        temperamentTextView.setText(temperament);

        originTitleTextView.setText("Origin:");
        nameTitleTextView.setText("Name of the breed:");
        temperamentTitleTextView.setText("Temperament:");


        new DownloadImageTask(catImageView)
                .execute(imageURL);
    }

    public void youveGotMail(String name, String origin, String temperament) {
        nameTextView.setText(name);
        originTextView.setText(origin);
        temperamentTextView.setText(temperament);


    }
}