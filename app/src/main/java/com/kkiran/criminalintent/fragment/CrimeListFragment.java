package com.kkiran.criminalintent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kkiran.criminalintent.R;
import com.kkiran.criminalintent.activity.CrimeActivity;
import com.kkiran.criminalintent.model.Crime;
import com.kkiran.criminalintent.model.CrimeLab;

import java.util.List;

/**
 * Created by kkanchamreddy on 1/10/16.
 */
public class CrimeListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CrimeAdapter mCrimeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mCrimeAdapter == null) {
            mCrimeAdapter = new CrimeAdapter(crimes);
            mRecyclerView.setAdapter(mCrimeAdapter);
        } else {
            mCrimeAdapter.notifyDataSetChanged();
        }

    }

    private class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckbox;

        private Crime mCrime;

        public CrimeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckbox= (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_checkbox);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckbox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
            Intent i = CrimeActivity.createNewIntent(getActivity(), mCrime.getId());
            startActivity(i);
        }
    }

    private class CrimeAdapter extends  RecyclerView.Adapter<CrimeViewHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_crime, parent, false);
            return new CrimeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeViewHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            //holder.mTitleTextView.setText(crime.getTitle());
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
