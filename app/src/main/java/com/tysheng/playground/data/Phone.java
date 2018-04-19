package com.tysheng.playground.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tysheng
 * Date: 14/11/17 3:02 PM.
 * Email: tyshengsx@gmail.com
 */

public class Phone implements Serializable{

    /**
     * country_code : 65
     * phone : 92476751
     */

    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("phone")
    private String phone;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
