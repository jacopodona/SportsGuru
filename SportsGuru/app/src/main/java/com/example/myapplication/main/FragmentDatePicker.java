package com.example.myapplication.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FragmentDatePicker extends Fragment {

    private final int INDEX = 3;
    private Spinner spinner;
    private MaterialButton back, confirm;
    private int dateIndicator;
    private DatePicker datePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_date_picker, container, false);

        spinner = v.findViewById(R.id.datepicker_spinner);
        back = v.findViewById(R.id.datepicker_back);
        confirm = v.findViewById(R.id.datepicker_confirm);
        datePicker = v.findViewById(R.id.datepicker_datepicker);

        setupBottoni();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.date_spinner, android.R.layout.simple_spinner_item);//per cambiare layout cambia ultimo parametro
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dateIndicator=position;
                if(dateIndicator==6){
                    datePicker.setVisibility(View.VISIBLE);
                }
                else{
                    datePicker.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    private void setupBottoni() {
        MainActivity activity = (MainActivity) getActivity();
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                Date date;
                String text = spinner.getSelectedItem().toString();
                switch (dateIndicator){
                    case 0://Ieri
                        cal.add(Calendar.DAY_OF_YEAR,-1);
                        break;
                    case 1://Ultimo mese
                        cal.add(Calendar.MONTH,-1);
                        break;
                    case 2://Ultimi 3 mesi
                        cal.add(Calendar.MONTH,-3);
                        break;
                    case 3://Questo anno
                        cal.add(Calendar.YEAR,-1);
                        break;
                    case 4://Ultimi 3 anni
                        cal.add(Calendar.YEAR,-3);
                        break;
                    case 5://In carriera
                        cal.add(Calendar.YEAR,-500);
                        break;
                    case 6://Data Manuale
                        cal.set(Calendar.YEAR, datePicker.getYear());
                        cal.set(Calendar.MONTH, datePicker.getMonth());
                        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                        break;
                }
                date=cal.getTime();
                //Toast.makeText(getContext(),"Data di partenza: "+formatter.format(date),Toast.LENGTH_LONG).show();
                activity.confermaData(formatter.format(date));
                activity.next(INDEX);
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