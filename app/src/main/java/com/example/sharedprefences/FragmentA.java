package com.example.sharedprefences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragmentA extends Fragment {
    EditText edt_name,edt_place;
    Button save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v= inflater.inflate(R.layout.fragment_a, container, false);
        edt_name=v.findViewById(R.id.ed_name);
        edt_place=v.findViewById(R.id.ed_place);
        save=v.findViewById(R.id.btn_save);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                  //  if ((!edt_name.equals(null) && (!edt_place.equals(null)))){
                        if(!edt_name.getText().toString().equals("") && !edt_place.getText().toString().equals(""))
                        {
                        String name=edt_name.getText().toString();
                        String place=edt_place.getText().toString();
                        Bundle bundle=new Bundle();
                        bundle.putString("namekey",name);
                        bundle.putString("placekey",place);
                        FragmentB fragmenta=new FragmentB();
                        fragmenta.setArguments(bundle);

                        sharedPrefs();

                        getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragmenta).commit();
                    }else {
                        Toast.makeText(getContext(),"pls enter the value" ,Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception e)
                {
                    Log.i("Data>>" , e.toString());
                }


            }

             private void sharedPrefs() {
                 String name=edt_name.getText().toString();
                 String place=edt_place.getText().toString();
                SharedPreferences sharedPref = getActivity() .getSharedPreferences("storage", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("namekey", name);
                editor.putString("placekey", place);
                editor.commit();
            }
        });

return v;
    }

}