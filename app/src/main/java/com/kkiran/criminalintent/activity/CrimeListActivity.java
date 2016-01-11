package com.kkiran.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.kkiran.criminalintent.fragment.CrimeListFragment;

/**
 * Created by kkanchamreddy on 1/10/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return  new CrimeListFragment();
    }
}
