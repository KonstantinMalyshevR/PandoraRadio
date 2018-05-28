package com.reklamar.testpandora.classes.api;

import com.reklamar.testpandora.classes.workclasses.SupportClass;

/**
 * Created by Developer on 18.05.18.
 */

public class PartnerLoginRequest {

    private String username;
    private String password;
    private String deviceModel;
    private String version;

    public PartnerLoginRequest() {
        this.username = SupportClass.PARTNER_USERNAME;
        this.password = SupportClass.PARTNER_PASSWORD;
        this.deviceModel = SupportClass.PARTNER_DEVICE_MODEL;
        this.version = SupportClass.PARTNER_VERSION_NUMBER;
    }
}
