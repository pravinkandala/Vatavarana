package io.pk.vatavarana;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragment;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SlideFragmentBuilder mSlideFragmentBuilder = new SlideFragmentBuilder().backgroundColor(R.color.colorOrange);
        if (Build.VERSION.SDK_INT >= 23)
            mSlideFragmentBuilder.possiblePermissions(new String[]{ android.Manifest.permission.ACCESS_COARSE_LOCATION}).neededPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET});
        final SlideFragment mSlider = mSlideFragmentBuilder.title(getString(R.string.intro_title)).description(getString(R.string.intro_description)).build();
        addSlide(mSlider, new MessageButtonBehaviour(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread loading = new Thread() {
                    public void run() {
                        try {
                            sleep(SPLASH_DISPLAY_LENGTH);
                            Intent main = new Intent(io.pk.vatavarana.IntroActivity.this, BaseActivity.class);
                            startActivity(main);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            finish();
                        }
                    }
                };

                loading.start();
            }
        },getString(R.string.intro_button)));
    }

}
