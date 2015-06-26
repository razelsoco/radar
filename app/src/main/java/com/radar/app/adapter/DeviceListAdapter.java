package com.radar.app.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.radar.app.R;
import com.radar.app.models.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razelsoco on 26/6/15.
 */
public class DeviceListAdapter extends BaseAdapter {
    List<Device> items = new ArrayList<Device>();
    Context context;

    public DeviceListAdapter(Context context, List<Device> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        if(items == null) return 0;
        return items.size();
    }

    @Override
    public Device getItem(int position) {
        if(position < items.size() && position >= 0)
            return items.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_deivce, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_device_name);
            holder.tv_type = (TextView) convertView.findViewById(R.id.tv_device_type);
            holder.tv_last_active = (TextView) convertView.findViewById(R.id.tv_device_last_active);
            holder.is_online = (ImageView)convertView.findViewById(R.id.iv_is_online);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();

        Device item = getItem(position);
        holder.tv_name.setText(item.getTitle());
        holder.tv_type.setText("Device type: "+item.getForm_factor());
        holder.tv_last_active.setText("Last active: 5hrs ago");
        holder.is_online.setSelected(item.is_online());
        return convertView;
    }

    public class ViewHolder {
        TextView tv_name, tv_type, tv_last_active;
        ImageView is_online;

    }
}
