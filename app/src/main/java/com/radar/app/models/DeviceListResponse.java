package com.radar.app.models;

import java.util.ArrayList;

/**
 * Created by razelsoco on 26/6/15.
 */
public class DeviceListResponse extends RadarResponse{
    private ArrayList<Device> devices;

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }
}
