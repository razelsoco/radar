package com.radar.app.models;

/**
 * Created by razelsoco on 26/6/15.
 */
public class Device {
    private String title;
    private String os_version;
    private String form_factor;
    private String screen_resolution;
    private String screen_size;
    private String longitude;
    private String latitude;
    private String in_proximity;
    private long last_ping;
    private boolean is_my_device;
    private boolean is_online;

    public String getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(String form_factor) {
        this.form_factor = form_factor;
    }

    public String getIn_proximity() {
        return in_proximity;
    }

    public void setIn_proximity(String in_proximity) {
        this.in_proximity = in_proximity;
    }

    public boolean is_my_device() {
        return is_my_device;
    }

    public void setIs_my_device(boolean is_my_device) {
        this.is_my_device = is_my_device;
    }

    public boolean is_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    public long getLast_ping() {
        return last_ping;
    }

    public void setLast_ping(long last_ping) {
        this.last_ping = last_ping;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getScreen_resolution() {
        return screen_resolution;
    }

    public void setScreen_resolution(String screen_resolution) {
        this.screen_resolution = screen_resolution;
    }

    public String getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(String screen_size) {
        this.screen_size = screen_size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
