package com.radar.app.network;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.radar.app.helper.ConstHelper;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by razelsoco on 12/6/15.
 */
public abstract class GsonRequest<T> extends Request<T> {
    public static final int SOCKET_TIMEOUT = 30000;
    public static final int DEFAULT_MAX_RETRIES = 1;
    private final Class<T> clazz;
    private final RequestHandler.ResponseListener<T> listener;
    private MultipartEntity entity;

    public GsonRequest(int method, String url, Class<T> clazz, RequestHandler.ResponseListener<T> listener) {

        super(method, ConstHelper.HOST + url, listener);
        this.clazz = clazz;
        this.listener = listener;
        this.setShouldCache(true);
        this.setRetryPolicy(new DefaultRetryPolicy(
                SOCKET_TIMEOUT,
                DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        if(method == Method.POST && getCustomParams() != null){
            Map<String,String> params = getCustomParams();
            entity = new MultipartEntity();
            for(Map.Entry<String,String> entry : params.entrySet()){

                try {
                    entity.addPart(entry.getKey(), new StringBody(entry.getValue()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headerMap = super.getHeaders();

        if (headerMap == null
                || headerMap.equals(Collections.emptyMap())) {
            headerMap = new HashMap<String, String>();
        }

        headerMap.put("Accept", "application/json");
        headerMap.put("Accept-Language", Locale.getDefault().toString());
        return headerMap;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Gson gson = new Gson();
            return Response.success(gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if(listener != null)
            listener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return getCustomParams();
    }

    public abstract Map<String,String> getCustomParams();

    @Override
    public String getBodyContentType() {
        if(getMethod() == Method.POST && entity != null) {
            String contentType = entity.getContentType().getValue();
            return entity.getContentType().getValue();
        }else return super.getBodyContentType();
    }

    @Override
    public byte[] getBody() {
        if(getMethod()== Method.POST && entity != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                entity.writeTo(bos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = bos.toByteArray();
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }else
            try {
                super.getBody();
            } catch (AuthFailureError authFailureError) {
                authFailureError.printStackTrace();
            }
        return new byte[0];
    }
}
