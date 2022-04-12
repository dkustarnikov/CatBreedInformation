package com.mitya.catbreedinformation;

import static com.mitya.catbreedinformation.Constants.breedNamesOnly;
import static com.mitya.catbreedinformation.Constants.cats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitya.catbreedinformation.helpers.MySingleton;
import com.mitya.catbreedinformation.model.Cat;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements SelectBreedFragment.onSelectBreedFragmentListener {

    //This is going to hold the cats. the key is the name of the breed for easier lookup.


    SelectBreedFragment mSelectBreedFragment;
    CatInformationFragment mCatInformationFragment;

    private static final String SELECT_BREED_TAG = "selectBreed";
    private static final String INFORMATION_TAG = "information";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String API_URL = "https://api.thecatapi.com/v1/breeds";

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mSelectBreedFragment == null) {
            mSelectBreedFragment = new SelectBreedFragment();
            fragmentManager.beginTransaction().add(R.id.selectBreedFragment, mSelectBreedFragment, SELECT_BREED_TAG).commit();
        }

        if (mCatInformationFragment == null) {
            mCatInformationFragment = new CatInformationFragment();
            fragmentManager.beginTransaction().add(R.id.breedInformationFragment, mCatInformationFragment, INFORMATION_TAG).commit();
        }

        Cat tempCat = new Cat();
        tempCat.setName("Please select a breed");
        tempCat.setOrigin("Please select a breed");
        tempCat.setTemperament("Please select a breed");
        cats.put("Please select a breed", new Cat());


        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        Log.d("Response", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            ObjectMapper mapper = new ObjectMapper();
                            try {
                                Cat cat = mapper.readValue(response.getString(i), Cat.class);
                                cats.put(cat.getName(), cat);
                                breedNamesOnly.add(cat.getName());
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_item, breedNamesOnly);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSelectBreedFragment.dropDownList.setAdapter(adapter);
                        System.out.println(1);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("x-api-key", Constants.API_KEY);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(getRequest);
    }


    @Override
    public void updateCatInfo(String name, String origin, String temperament, String imageURL) {
        mCatInformationFragment.youveGotMail(name, origin, temperament, imageURL);
    }

    @Override
    public void updateCatInfo(String name, String origin, String temperament) {
        mCatInformationFragment.youveGotMail(name, origin, temperament);
    }
}