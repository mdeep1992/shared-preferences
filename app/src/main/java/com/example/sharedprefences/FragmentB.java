package com.example.sharedprefences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentB extends Fragment {
    TextView tx_name, tx_place;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b, container, false);
        tx_name = v.findViewById(R.id.txt_name);
        tx_place = v.findViewById(R.id.txt_place);
try{
    Bundle bundle=this.getArguments();
    String getname= bundle.getString("namekey");
    String getplace= bundle.getString("placekey");
    initSharedPref();

//    FragmentB fragmentb=new FragmentB();
//    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragmentb).commit();
}catch (Exception e){
    Log.i("error>>",e.toString());
}


        return v;
    }

    private void initSharedPref() {
        SharedPreferences sharedPref = getContext().getSharedPreferences("storage", Context.MODE_PRIVATE);
        String name = sharedPref.getString("namekey" , "");
        String place = sharedPref.getString("placekey" , "");

        Log.i("name>>>>", name);

        tx_name.setText(name);
        tx_place.setText(place);


    }
}