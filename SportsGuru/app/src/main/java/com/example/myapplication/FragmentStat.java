package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentStat extends Fragment {

    private final int INDEX=2;
    private Button next,back;
    private TextInputEditText stat,anno;
    private TextInputLayout statLayout,annoLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stat,container,false);

        next=v.findViewById(R.id.stat_next);
        back=v.findViewById(R.id.stat_back);
        stat=v.findViewById(R.id.stat_textField_stat);
        anno=v.findViewById(R.id.stat_textField_anno);
        statLayout=v.findViewById(R.id.stat_textLayout_stat);
        annoLayout=v.findViewById(R.id.stat_textLayout_anno);

        setupBottoni();

        return v;
    }

    private void setupBottoni() {
        MainActivity activity= (MainActivity) getActivity();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(stat.getText()) && !TextUtils.isEmpty(anno.getText())){
                    activity.next(INDEX);
                    activity.confermaStatistica(stat.getText().toString(),anno.getText().toString());
                    clearErrors();
                }
                else{
                    if(TextUtils.isEmpty(stat.getText())){
                        statLayout.setError(getResources().getString(R.string.insert_stat_error));
                    }
                    if(TextUtils.isEmpty(anno.getText())){
                        annoLayout.setError(getResources().getString(R.string.insert_year_error));
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditText();
                clearErrors();
                activity.previous(INDEX);
            }
        });
    }

    private void clearEditText() {
        stat.setText("");
        anno.setText("");
    }

    private void clearErrors(){
        statLayout.setError(null);
        anno.setError(null);
    }
}
