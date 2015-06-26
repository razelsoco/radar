package com.radar.app.models;

import java.util.ArrayList;

/**
 * Created by razelsoco on 26/6/15.
 */
public class DeviceDetailResponse extends RadarResponse{
    private Device device;

    public Device getDevices() {
        return device;
    }

    public void setDevices(Device devices) {
        this.device = devices;
    }
}
