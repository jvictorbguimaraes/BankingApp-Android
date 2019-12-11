package com.example.bank;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private int nooftabs;

    public PageAdapter(FragmentManager fm, int noOftabs) {
        super(fm);
        this.nooftabs = noOftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new tabHome();
            case 1:
                return new tabTransfers();
            case 2:
                return new tabBills();
            case 3:
                return new tabProfile();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return nooftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
         return POSITION_NONE;
    }
}
