package com.reklamar.testpandora.classes.workclasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.reklamar.testpandora.R;

import org.apache.commons.codec.binary.Hex;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Developer on 21.05.18.
 */

public class SupportClass {

    public final static String BASE_URL = "https://tuner.pandora.com:443/";
    public final static String ENCRYPT_KEY = "ENCRYPT_KEY";
    public final static String DECRYPT_KEY = "DECRYPT_KEY";

    public final static String PARTNER_USERNAME = "android";
    public final static String PARTNER_PASSWORD = "KEY_PASSWORD";
    public final static String PARTNER_DEVICE_MODEL = "android-generic";
    public final static String PARTNER_VERSION_NUMBER = "5";

    //decrypt JSON returned from pandora api using blowfish algorithm
    public static String decrypt(String data)throws Exception {
        byte[] encryptedData = Hex.decodeHex(data.toCharArray());
        SecretKeySpec key = new SecretKeySpec(DECRYPT_KEY.getBytes("UTF8"), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encryptedData);
        return new String(Arrays.copyOfRange(decrypted, 4, 14)); //this decrypt function is only used for server time which has 4 junk bytes
    }

    public static void ToastMessage(Context context, String value){
        Toast toast = Toast.makeText(context, value, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static String checkStringNullAndTrim(String value){
        if(value != null){
            return value.trim();
        }else{
            return "";
        }
    }

    public static DisplayImageOptions displayImageOptions(){
        return new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(800))
                .build();
    }
}
