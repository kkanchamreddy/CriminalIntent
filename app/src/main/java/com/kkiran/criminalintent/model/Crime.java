package com.kkiran.criminalintent.model;

import java.util.UUID;

/**
 * Created by kkanchamreddy on 1/9/16.
 */
public class Crime {
    private UUID mId;
    private String mTitle;

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
