package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;


public class FragmentDatePicker extends Fragment {

    private final int INDEX = 3;
    private Spinner spinner;
    private MaterialButton back, confirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_date_picker, container, false);

        spinner = v.findViewById(R.id.datepicker_spinner);
        back = v.findViewById(R.id.datepicker_back);
        confirm = v.findViewById(R.id.datepicker_confirm);

        setupBottoni();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.date_spinner, android.R.layout.simple_spinner_item);//per cambiare layout cambia ultimo parametro
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    private void setupBottoni() {
        MainActivity activity = (MainActivity) getActivity();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = spinner.getSelectedItem().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.previous(INDEX);
            }
        });
    }
}