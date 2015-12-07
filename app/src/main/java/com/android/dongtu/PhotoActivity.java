package com.android.dongtu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.dongtu.data.AlbumDetail;
import com.android.dongtu.ui.fragment.PhotoFragment;

/**
 * Created by kuroterry on 15/12/3.
 */
public class PhotoActivity extends BaseActivity{

    public static final String KEY_DETAIL = "key_detail";

    @Override
    void init() {
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if(intent != null) {
            Bundle bundle = intent.getExtras();
            AlbumDetail albumDetail = (AlbumDetail) bundle.getSerializable(KEY_DETAIL);
            Fragment fragment = PhotoFragment.instance(albumDetail, 0);
            addFragment(fragment, true);
        }

    }

    @Override
    int getContainerRes() {
        return R.id.frm_container;
    }

}