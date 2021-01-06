package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String nomeAtleta,cognomeAtleta,statisticaAtleta,durataAtleta;


    private static final int NUM_PAGES = 3;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private RelativeLayout layout_navigazione;

    WelcomeFragment welcome;
    FragmentAtleta atleta;
    FragmentStat stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome=new WelcomeFragment();
        atleta=new FragmentAtleta();
        stat=new FragmentStat();

        viewPager=findViewById(R.id.main_viewpager);

        pagerAdapter=new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        //viewPager.setUserInputEnabled(false);//disable swipe dell'utente

    }

    public void previous(int i) {
        if(i>0) {
            viewPager.setCurrentItem(i-1);
        }
    }

    public void next(int i) {
        if(i<2) {
            viewPager.setCurrentItem(i+1);
        }
    }


    public void confermaAtleta(String nome, String cognome){
        nomeAtleta=nome;
        cognomeAtleta=cognome;
    }

    public void confermaStatistica(String statistica,String durata){
        statisticaAtleta=statistica;
        durataAtleta=durata;
        Log.d("Request","Atleta: "+nomeAtleta+" "+cognomeAtleta+"\nStatistica: "+statisticaAtleta+"\nAnno: "+durataAtleta);
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