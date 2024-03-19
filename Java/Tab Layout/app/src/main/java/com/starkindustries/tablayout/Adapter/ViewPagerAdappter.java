package com.starkindustries.tablayout.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.starkindustries.tablayout.Calls_Fragment;
import com.starkindustries.tablayout.Chat_Fragment;
import com.starkindustries.tablayout.Status_Fragment;

public class ViewPagerAdappter extends FragmentPagerAdapter {
    public ViewPagerAdappter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "Chat";
        else if(position==1)
            return "Status";
        else
            return "Calls";
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new Chat_Fragment();
        else if(position==1)
            return new Status_Fragment();
        else
            return new Calls_Fragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

}
