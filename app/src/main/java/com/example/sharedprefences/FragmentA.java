package com.example.sharedprefences;

import static androidx.security.crypto.EncryptedSharedPreferences.*;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class FragmentA extends Fragment {
    EditText edt_name, edt_place;
    Button save, fetch_data;
    TextView secret_name, secret_place;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        edt_name = v.findViewById(R.id.ed_name);
        edt_place = v.findViewById(R.id.ed_place);
        save = v.findViewById(R.id.btn_save);
        secret_name = v.findViewById(R.id.txt_secretname);
        secret_place = v.findViewById(R.id.txt_secretplace);
        fetch_data = v.findViewById(R.id.btn_fetch);

        fetch_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sharedPrefsfetch();
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            private void sharedPrefsfetch() throws GeneralSecurityException, IOException {
                String name = edt_name.getText().toString();
                String place = edt_place.getText().toString();

                String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

                Context context;
                SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                        "security",
                        masterKeyAlias,
                        getContext(),
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("namekey1", name);
                editor.putString("placekey1", place);
                editor.commit();
//                SharedPreferences sharedPreference = getContext().getSharedPreferences("storage", Context.MODE_PRIVATE);
                String name1 = sharedPreferences.getString("namekey1", "");
                String place1 = sharedPreferences.getString("placekey1", "");
                secret_name.setText(name1);
                secret_place.setText(place1);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!edt_name.getText().toString().equals("") && !edt_place.getText().toString().equals("")) {
                        sharedPrefs();
                        FragmentB fragmenta = new FragmentB();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmenta).commit();
                    } else {
                        Toast.makeText(getContext(), "pls enter the value", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    Log.i("Data>>", e.toString());
                }


            }

            private void sharedPrefs() throws GeneralSecurityException, IOException {
                String name = edt_name.getText().toString();
                String place = edt_place.getText().toString();
                String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                SharedPreferences sharedPreference = EncryptedSharedPreferences.create(
                        "savefetch",
                        masterKeyAlias,
                        getContext(),
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
               // SharedPreferences sharedPreferences = getContext().getSharedPreferences("savefetch", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putString("namekey", name);
                editor.putString("placekey", place);
                editor.commit();
            }
        });

        return v;
    }

}