package com.programmer.seratic.androidtest;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private MusicLibraryAdapter adapter;
    private List<MusicLibrary> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
        recyclerView = findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        adapter = new MusicLibraryAdapter(this, albumList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareAlbums();
        try
        {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.paralax_image));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initCollapsingToolbar()
    {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {
                if (scrollRange == -1)
                {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0)
                {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                }
                else if (isShow)
                {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void prepareAlbums()
    {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.song1,
                R.drawable.song2,
                R.drawable.song3,
                R.drawable.song4
        };
        // Load Songs and Albums
        albumList.add(new Album(getString(R.string.artist1),3,covers[0]));
        albumList.add(new Album(getString(R.string.artist2),3,covers[1]));
        albumList.add(new Album(getString(R.string.artist3),3,covers[2]));
        albumList.add(new Album(getString(R.string.artist4),3,covers[3]));
        albumList.add(new Song("daemons","imagine dragons","2001",covers[4]));
        albumList.add(new Song("daemons","imagine dragons","2001",covers[5]));
        albumList.add(new Song("daemons","imagine dragons","2001",covers[6]));
        albumList.add(new Song("daemons","imagine dragons","2001",covers[7]));
        adapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration
    {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge)
        {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;
            if (includeEdge)
            {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;
                if (position < spanCount)
                {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            }
            else
            {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount)
                {
                    outRect.top = spacing;
                }
            }
        }
    }

    private int dpToPx()
    {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));
    }
}
