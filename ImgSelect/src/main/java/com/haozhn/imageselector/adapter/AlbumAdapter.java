package com.haozhn.imageselector.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haozhn.imageselector.util.ImageLoader;
import com.haozhn.imageselector.R;
import com.haozhn.imageselector.model.Album;
import com.haozhn.imageselector.model.Photo;

import java.util.Locale;

/**
 * AlbumAdapter
 * Created by hao on 2016/4/6.
 */
public class AlbumAdapter extends AbsBaseAdapter<Album> {
    public AlbumAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final Album album = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_album, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivCover= (ImageView) convertView.findViewById(R.id.iv_cover);
            viewHolder.tvAlbumName= (TextView) convertView.findViewById(R.id.tv_album_name);
            viewHolder.photoNum= (TextView) convertView.findViewById(R.id.tv_photo_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvAlbumName.setText(album.getName());
        if (album.getPhotoList() != null && album.getPhotoList().size() > 0) {
            Photo p = album.getPhotoList().get(0);
            if (p != null) {
                ImageLoader.loadPhoto(context, p.getPath(), viewHolder.ivCover);
            }

            viewHolder.photoNum.setVisibility(View.VISIBLE);
            viewHolder.photoNum.setText(String.format(Locale.getDefault(),"(%d)", album.getPhotoList().size()));
        }else{
            viewHolder.photoNum.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView ivCover;
        TextView tvAlbumName;
        TextView photoNum;
    }
}
