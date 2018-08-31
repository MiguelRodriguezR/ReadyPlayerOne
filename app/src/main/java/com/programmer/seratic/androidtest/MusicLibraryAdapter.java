package com.programmer.seratic.androidtest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
public class MusicLibraryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private List<MusicLibrary> albumList;


    public MusicLibraryAdapter(Context context, List<MusicLibrary> albumList)
    {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Log.d("invocacion de holder",""+ parent.getContext());
        switch (viewType)
        {
            case MusicLibrary.TYPE_ALBUM:
                View albumView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.album_card,
                                parent,
                                false);
                return new ViewHolderAlbum(albumView);
            case MusicLibrary.TYPE_SONG:
                View songView = inflater.inflate(
                        R.layout.song_card,
                        parent,
                        false);
                return new ViewHolderSong(songView);
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        //Log.d("invocacion de holder",""+ albumList.get(position).getType());
        switch (holder.getItemViewType())
        {


            case MusicLibrary.TYPE_ALBUM:

                // Code for View Album
                Album m = (Album) albumList.get(position);
                ViewHolderAlbum va = (ViewHolderAlbum)holder;
                va.albumCount.setText(""+m.getNumOfSongs());
                va.albumTitle.setText(m.getName());
                va.albumThumb.setImageResource(m.getThumbnail());
                va.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showPopupMenu(view);
                    }
                });
               // showPopupMenu();


                break;
            case MusicLibrary.TYPE_SONG:

                Song s = (Song) albumList.get(position);
                ViewHolderSong vs = (ViewHolderSong) holder;
                vs.songArtist.setText(s.getArtist());
                vs.songTitle.setText(s.getTitle());
                vs.songYear.setText(s.getYear());
                vs.songImage.setImageResource(s.getTagImage());
                vs.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showPopupMenuSong(view);
                    }
                });

                //showPopupMenuSong(vs.itemView);


                break;
        }
    }

    private void showPopupMenu(View view)
    {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    private void showPopupMenuSong(View view)
    {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_song, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    public int getItemViewType(int position) {
        return albumList.get(position).getType();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener
    {
        public MyMenuItemClickListener()
        {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem)
        {
            switch (menuItem.getItemId())
            {
                case R.id.action_add_favourite:
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(context, "Play next", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.action_play_song:
                    Toast.makeText(context, "Now is Playing", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_enqueue:
                    Toast.makeText(context, "Song add to play list", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount()
    {
        return albumList.size();
    }

    public class ViewHolderAlbum extends RecyclerView.ViewHolder
    {
        TextView albumTitle, albumCount;
        ImageView albumThumb, albumOverflow;

        ViewHolderAlbum(View view)
        {
            super(view);
            albumTitle      = view.findViewById(R.id.title);
            albumCount      = view.findViewById(R.id.count);
            albumThumb      = view.findViewById(R.id.thumbnail);
            albumOverflow   = view.findViewById(R.id.overflow);
        }
    }

    public class ViewHolderSong extends RecyclerView.ViewHolder
    {
        TextView songTitle, songArtist, songYear;
        ImageView songImage, songActions;

        ViewHolderSong(View view)
        {
            super(view);
            songTitle = view.findViewById(R.id.title_song);
            songArtist = view.findViewById(R.id.title_artist);
            songYear = view.findViewById(R.id.year_song);
            songImage = view.findViewById(R.id.image_song);
            songActions = view.findViewById(R.id.song_actions);
        }
    }
}
