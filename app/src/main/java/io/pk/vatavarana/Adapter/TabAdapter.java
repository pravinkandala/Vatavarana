package io.pk.vatavarana.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"Forecast", "Map"};


    public TabAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


    @Override
    public Fragment getItem(int position) {
        //return null;
        switch (position){
            case 0:
                return new PageOne();

            case 1:
                return new PageTwo();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

}
