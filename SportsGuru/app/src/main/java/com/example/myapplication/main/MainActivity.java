package com.example.myapplication.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.myapplication.modules.Richiesta;
import com.example.myapplication.presentation.PresentationActivity;
import com.example.myapplication.R;
import com.example.myapplication.welcome.WelcomeFragment;

public class MainActivity extends AppCompatActivity {


    private Richiesta richiesta;


    private static final int NUM_PAGES = 5;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private RelativeLayout layout_navigazione;

    WelcomeFragment welcome;
    FragmentAtleta atleta;
    FragmentStat stat;
    FragmentConferma conferma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        richiesta=new Richiesta();
        welcome=new WelcomeFragment();
        atleta=new FragmentAtleta();
        stat=new FragmentStat();
        conferma=new FragmentConferma();

        richiesta=new Richiesta();

        viewPager=findViewById(R.id.main_viewpager);

        pagerAdapter=new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setUserInputEnabled(false);//disable swipe dell'utente

    }

    public void previous(int i) {
        if(i>0) {
            viewPager.setCurrentItem(i-1);
        }
    }

    public void next(int i) {
        if(i<NUM_PAGES-1) {
            viewPager.setCurrentItem(i+1);
        }
    }


    public void confermaAtleta(String nome, String cognome){
        richiesta.setNome(nome);
        richiesta.setCognome(cognome);
    }

    public void confermaStatistica(String statistica){
        richiesta.setStatistica(statistica);
    }

    public void confermaData(String data){
        richiesta.setData(data);
    }

    public void research() {
        Intent i=new Intent(this, PresentationActivity.class);
        Log.d("Request","Atleta: "+richiesta.getNome()+" "+richiesta.getCognome()+"\nStatistica: "+richiesta.getStatistica()+"\nAnno: "+richiesta.getData());
        i.putExtra("request",richiesta);
        startActivity(i);
        finish();
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
            else if(position==3){
                f=new FragmentDatePicker();
            }
            else if(position==4){
                f=new FragmentConferma();
            }
            return f;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onBackPressed() {
        int i=viewPager.getCurrentItem();
        if(i>0){
            previous(i);
        }
        else{
            super.onBackPressed();
        }
    }

    public Richiesta getRichiesta() {
        return richiesta;
    }
}