package com.kkiran.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kkanchamreddy on 1/10/16.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public Crime get(UUID uid) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(uid)) {
                return crime;
            }
        }
        return null;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public void  addCrime(Crime crime) {
        mCrimes.add(crime);
    }

    public boolean deleteCrime(UUID crimeId) {
        return mCrimes.remove(this.get(crimeId));
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
    }
}
