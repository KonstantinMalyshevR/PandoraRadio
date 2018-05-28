package com.reklamar.testpandora.classes.api;

import com.reklamar.testpandora.classes.workclasses.SupportClass;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by Developer on 19.05.18.
 */

public class EncryptionInterceptor implements Interceptor {

    private static final String GET = "GET";
    private static final String POST = "POST";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String urtStr = request.url().toString();
        if (GET.equals(request.method())) {
            /** do encrypt action for url **/
            request = request.newBuilder().url(urtStr).build();
        } else if (POST.equals(request.method())) {
            MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
            RequestBody oldBody = request.body();
            Buffer buffer = new Buffer();
            oldBody.writeTo(buffer);
            String params = toJson(buffer.readUtf8());
            String strNewBody = params;
            /** do encrypt action for strNewBody **/

            try {
                strNewBody = encrypt(strNewBody);
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(mediaType, strNewBody);
            request = request.newBuilder().url(urtStr).header("Content-Type",
                    body.contentType().toString()).header("Content-Length",
                    String.valueOf(body.contentLength())).method(request.method(), body).build();
        }
        return chain.proceed(request);
    }

    private String toJson(String params) {
        try {
            new JSONObject(params);
            return params;
        } catch (JSONException e) {
            JSONObject jsonObject = new JSONObject();
            String[] maps = params.split("&");
            for (String map : maps) {
                String[] kv = map.split("=");
                try {
                    jsonObject.put(kv[0], kv.length == 2 ? kv[1] : "");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            return jsonObject.toString();
        }
    }

    //encrypt data using blowfish algorithm
    public String encrypt(String data)throws Exception {
        SecretKeySpec key = new SecretKeySpec(SupportClass.ENCRYPT_KEY.getBytes("UTF8"), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return new String(Hex.encodeHex(cipher.doFinal(data.getBytes("UTF8"))));
    }
}