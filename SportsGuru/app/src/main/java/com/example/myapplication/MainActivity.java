package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    String nomeAtleta,cognomeAtleta,statisticaAtleta,durataAtleta;


    private Button next,back;
    private static final int NUM_PAGES = 3;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private RelativeLayout layout_navigazione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back=findViewById(R.id.main_previous);
        next=findViewById(R.id.main_next);
        layout_navigazione=findViewById(R.id.main_layout_navigazione);
        viewPager=findViewById(R.id.main_pager);

        pagerAdapter=new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        //viewPager.setUserInputEnabled(false);//disable swipe dell'utente

        setupBottoni();
    }

    private void setupBottoni() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous(viewPager.getCurrentItem());
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(viewPager.getCurrentItem());
            }
        });
    }

    public void confermaAtleta(String nome, String cognome){
        nomeAtleta=nome;
        cognomeAtleta=cognome;
    }

    public void confermaStatistica(String statistica,String durata){
        statisticaAtleta=statistica;
        durataAtleta=durata;
    }

    public void next(int index){
        if(index<NUM_PAGES){
            viewPager.setCurrentItem(index+1);
            if(index==0){
                layout_navigazione.setVisibility(View.VISIBLE);
            }
        }
    }

    public void previous(int index){
        if(index>0){
            viewPager.setCurrentItem(index-1);
        }
        if(index==1){
            layout_navigazione.setVisibility(View.GONE);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            Fragment f=new Fragment();
            if(position == 0) {
                f= new WelcomeFragment();
            }
            else if(position==1){
                f=new FragmentAtleta();
            }
            else if(position==2){
                f=new FragmentStat();
            }
            return f;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}