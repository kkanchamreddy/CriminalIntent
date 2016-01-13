package com.kkiran.criminalintent.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.kkiran.criminalintent.fragment.CrimeFragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.kkiran.criminalintent.activity.crime_id";

    public static Intent createNewIntent(Context context, UUID crimId) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimId);
        return intent;
    }
    @Override
    protected  Fragment createFragment(){
        UUID uuid = (UUID)getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(uuid);
    }
}
