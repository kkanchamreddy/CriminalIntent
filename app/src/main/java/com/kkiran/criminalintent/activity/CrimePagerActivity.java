package com.kkiran.criminalintent.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.kkiran.criminalintent.R;
import com.kkiran.criminalintent.fragment.CrimeFragment;
import com.kkiran.criminalintent.model.Crime;
import com.kkiran.criminalintent.model.CrimeLab;

import java.util.List;
import java.util.UUID;

/**
 * Created by kkanchamreddy on 1/14/16.
 */
public class CrimePagerActivity extends AppCompatActivity implements CrimeFragment.Callbacks {
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    private static final String EXTRA_CRIME_ID = "com.kkiran.criminalintent.crime_id";

    public static Intent createNewIntent(Context context, UUID uuid){
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, uuid);
        return  intent;
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager)findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
