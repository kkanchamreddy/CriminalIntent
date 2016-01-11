package com.kkiran.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.kkiran.criminalintent.fragment.CrimeFragment;

public class CrimeActivity extends SingleFragmentActivity {
    @Override
    protected  Fragment createFragment(){
        return  new CrimeFragment();
    }
}
