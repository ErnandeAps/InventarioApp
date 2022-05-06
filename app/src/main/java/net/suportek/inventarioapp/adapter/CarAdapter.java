package net.suportek.inventarioapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import net.suportek.inventarioapp.R;
import net.suportek.inventarioapp.application.CustomApplication;
import net.suportek.inventarioapp.domain.Car;

import java.util.List;

/**
 * Created by viniciusthiengo on 2/1/15.
 */
public class CarAdapter extends BaseAdapter {
    private List<Car> list;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;


    public CarAdapter(Context c, List<Car> l){
        list = l;
        inflater = (LayoutInflater) c.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = ((CustomApplication) ((Activity) c).getApplication()).getImageLoader();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view == null){
            view = inflater.inflate( R.layout.car, null);
            holder = new ViewHolder();
            view.setTag(holder);

            holder.ivImg = (ImageView) view.findViewById(R.id.ivImg);
            holder.tvModel = (TextView) view.findViewById(R.id.tvModel);
            holder.tvBrand = (TextView) view.findViewById(R.id.tvBrand);
            holder.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        holder.ivImg.setImageBitmap(null);
        imageLoader.displayImage(list.get(position).getImagePath(), holder.ivImg);
        holder.tvModel.setText(list.get(position).getModel());
        holder.tvBrand.setText(list.get(position).getBrand());
        holder.tvPrice.setText(list.get(position).getPrice());

        return view;
    }


    // INNER CLASS
        private static class ViewHolder{
            ImageView ivImg;
            TextView tvModel;
            TextView tvBrand;
            TextView tvPrice;
        }
}
