package com.mitya.catbreedinformation;

import static com.mitya.catbreedinformation.Constants.cats;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.mitya.catbreedinformation.model.Cat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectBreedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectBreedFragment extends Fragment {

    Spinner dropDownList;

    private onSelectBreedFragmentListener mCallback;

    public SelectBreedFragment() {
        // Required empty public constructor
    }


    public static SelectBreedFragment newInstance(String param1, String param2) {
        SelectBreedFragment fragment = new SelectBreedFragment();
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


        View v = inflater.inflate(R.layout.fragment_select_breed, container, false);
        dropDownList = v.findViewById(R.id.breedsListSpinner);
        dropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedOption = adapterView.getSelectedItem().toString();
                Cat cat = cats.get(selectedOption);

                if (cat != null && cat.getImage() != null && cat.getImage().getUrl() != null && !cat.getImage().getUrl().isEmpty()) {
                    mCallback.updateCatInfo(cat.getName(), cat.getOrigin(), cat.getTemperament(), cat.getImage().getUrl());
                } else {
                    mCallback.updateCatInfo("Please select a breed", "Please select a breed", "Please select a breed");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mCallback.updateCatInfo("Please select a breed", "Please select a breed", "Please select a breed");
            }
        });

        return v;
    }

    // This is the interface that the Activity will implement
    // so that this Fragment can communicate with the Activity.
    public interface onSelectBreedFragmentListener {
        void updateCatInfo(String name, String origin, String temperament, String imageURL);

        void updateCatInfo(String name, String origin, String temperament);
    }

    // This method insures that the Activity has actually implemented our
    // listener and that it isn't null.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onSelectBreedFragmentListener) {
            mCallback = (onSelectBreedFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGreenFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

}

