package com.android.dongtu.ui.fragment;

import android.os.Message;
import android.view.View;

import com.android.dongtu.MainActivity;
import com.android.dongtu.adapter.AbstractAlbumAdapter;
import com.android.dongtu.adapter.AlbumSummaryAdapter;
import com.android.dongtu.data.AlbumSummary;
import com.android.dongtu.data.Albums;

import java.util.List;

/**
 * Created by kuroterry on 15/12/2.
 */
public class AlbumSummaryFragment extends AbstractAlbumFragment {

    public static AlbumSummaryFragment instance(){
        AlbumSummaryFragment fragment = new AlbumSummaryFragment();
        return fragment;
    }

    @Override
    public Object load() {
        isLoading = true;
        Object object;
        if(adapter.getItemCount() != 0) {
            object = abstractLoader.loadAlbumSummary(adapter.getItemCount());
        } else {
            object = abstractLoader.loadAlbumSummary();
        }
        isLoading = false;
        return object;
    }

    @Override
    public void initAdapter() {
        adapter = new AlbumSummaryAdapter();
    }

    @Override
    public void afterInit(View view) {
        adapter.setOnItemClickListener(new AbstractAlbumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                callback.onFragmentCallback(MainActivity.MAIN_ALBUMDETAIL, view, ((List<AlbumSummary>) adapter.getData()).get(position));
            }
        });
//        adapter.setOnGetViewListener(new AlbumSummaryAdapter.OnGetViewListener() {
//            @Override
//            public void onBindView(int position) {
//                getMoreImagesIfNeeded(position, adapter.getItemCount());
//            }
//        });
    }

    @Override
    public void loadMore(Message message) {
        if (!isLoading && message.obj != null) {
            Albums albums = (Albums) message.obj;
            if(albums.getSize() == 0) {
                return;
            }
            lastData = albums;
            List<AlbumSummary> data = albums.albumSummaries;
            adapter.add(data);
        }
    }

}
