package com.itheima.loadimages;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;

import com.itheima.loadimages.model.ImageData;
import com.itheima.loadimages.util.ImageUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Universal Image Loader 加载本地图片
 */
public class AlbumActivity extends Activity implements AdapterView.OnItemClickListener{


    private GridView gridview;
    private List<ImageData> mList;
    private Context context;
    private AlbumAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_layout);
        initView();
        context = this;
        mList = ImageUtil.getInstance().getMediaImage(context);
        mAdapter = new AlbumAdapter(context, mList);
        gridview.setAdapter(mAdapter);
    }

    private void initView() {
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CheckBox cbBox = (CheckBox) view.findViewById(R.id.cBox);
        //局部刷新
        mList.get(position).isChecked = !cbBox.isChecked();
        cbBox.setChecked(!cbBox.isChecked());
    }

    /***
     * GridView Adapter
     */
    private static class AlbumAdapter extends ArrayAdapter<ImageData> {

        private final ImageLoader imageLoader;
        private final DisplayImageOptions options;

        public AlbumAdapter(Context context, List<ImageData> objects) {
            super(context, 0, objects);
            imageLoader = ImageLoader.getInstance();
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.default_bg)
                    .showImageForEmptyUri(R.drawable.default_bg)
                    .showImageOnFail(R.drawable.default_bg)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item_layout, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.chBox = (CheckBox) convertView.findViewById(R.id.cBox);
                viewHolder.imaView = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ImageData item = getItem(position);
            Log.e("result", item.isChecked +"");
            viewHolder.chBox.setChecked(item.isChecked);
            imageLoader.displayImage(item.url, viewHolder.imaView, options);
            return convertView;
        }
    }

    private static class ViewHolder {
        ImageView imaView;
        CheckBox chBox;
    }
}
