package aman.com.moovi.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import aman.com.moovi.R;
import aman.com.moovi.ui.adapter.MainViewPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.tab_layout) TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupTabs();
    }

    private void setupTabs(){
        setSupportActionBar(mToolbar);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.movie_tab));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tv_show_tab));
        mTabLayout.addOnTabSelectedListener(this);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), 2);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
