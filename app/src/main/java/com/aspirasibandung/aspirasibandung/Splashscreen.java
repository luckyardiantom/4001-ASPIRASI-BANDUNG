package com.aspirasibandung.aspirasibandung;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class Splashscreen extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //menambahkan background
        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);
//
        //menambahkan logo
        configSplash.setLogoSplash(R.drawable.aspirasi);
        configSplash.setAnimLogoSplashDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.FadeInUp);

        //menambahkan title
        configSplash.setTitleSplash("Bandung Juara");
        configSplash.setTitleTextColor(R.color.splash_title);
        configSplash.setAnimTitleDuration(1000);
        configSplash.setTitleTextSize(20f);
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.FadeIn);
    }
    @Override
    public void animationsFinished(){
        startActivity(new Intent(Splashscreen.this, TampilanAwal.class));
    }
}
