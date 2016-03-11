package com.kkiran.criminalintent.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.kkiran.criminalintent.R;
import com.kkiran.criminalintent.fragment.CrimeFragment;
import com.kkiran.criminalintent.fragment.CrimeListFragment;
import com.kkiran.criminalintent.model.Crime;

/**
 * Created by kkanchamreddy on 1/10/16.
 */
public class CrimeListActivity extends SingleFragmentActivity implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

    @Override
    protected Fragment createFragment(){
        return  new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.createNewIntent(this, crime.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    public void onCrimeUpdated(Crime crime) {
        CrimeListFragment listFragment = (CrimeListFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}
