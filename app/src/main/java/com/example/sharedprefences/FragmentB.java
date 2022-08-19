package com.example.sharedprefences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;


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
       // initSharedPref();
        try {
            sharedPrefs();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }

    private void initSharedPref()  {


           SharedPreferences sharedPreferences = getContext().getSharedPreferences("savefetch", Context.MODE_PRIVATE);
            String name2 =sharedPreferences.getString("namekey", "");
            String place2 = sharedPreferences.getString("placekey", "");
            tx_name.setText(name2);
            tx_place.setText(place2);






    }

    private void sharedPrefs() throws GeneralSecurityException, IOException {

        String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        SharedPreferences sharedPreference = EncryptedSharedPreferences.create(
                "savefetch",
                masterKeyAlias,
                getContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

        String name  = sharedPreference.getString("namekey" , "");
        String place  = sharedPreference.getString("placekey" , "");
        tx_name.setText(name);
        tx_place.setText(place);
    }
}