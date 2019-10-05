package com.taskapp.onboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.taskapp.R;

public class OnBoardActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter2 adapter = new ViewPagerAdapter2(getSupportFragmentManager(), 0);
        adapter.AddFragment(new FragmentPage1(), "1");
        adapter.AddFragment(new FragmentPage2(), "2");
        adapter.AddFragment(new FragmentPage3(), "3");
        //adapter set up
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
        initViewPAger();
    }

    private void initViewPAger() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 3169));

    }


}
