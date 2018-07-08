package com.omar.carlist.app.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omar.carlist.utils.LocaleHelper;

public class AuctionInfo {

    @SerializedName("bids")
    @Expose
    private int bids;
    @SerializedName("endDate")
    @Expose
    private int endDate;
    @SerializedName("endDateEn")
    @Expose
    private String endDateEn;
    @SerializedName("endDateAr")
    @Expose
    private String endDateAr;
    @SerializedName("currencyEn")
    @Expose
    private String currencyEn;
    @SerializedName("currencyAr")
    @Expose
    private String currencyAr;
    @SerializedName("currentPrice")
    @Expose
    private Integer currentPrice;
    @SerializedName("minIncrement")
    @Expose
    private int minIncrement;
    @SerializedName("lot")
    @Expose
    private int lot;
    @SerializedName("priority")
    @Expose
    private int priority;
    @SerializedName("VATPercent")
    @Expose
    private int vATPercent;
    @SerializedName("isModified")
    @Expose
    private int isModified;
    @SerializedName("itemid")
    @Expose
    private int itemid;
    @SerializedName("iCarId")
    @Expose
    private int iCarId;
    @SerializedName("iVinNumber")
    @Expose
    private String iVinNumber;

    public int getBids() {
        return bids;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public String getEndDateString(String local) {
        return local.equals(LocaleHelper.LANGUAGE_ARABIC) ? endDateAr : endDateEn;
    }


    public String getCurrency(String local) {
        return local.equals(LocaleHelper.LANGUAGE_ARABIC) ? currencyAr : currencyEn;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public int getMinIncrement() {
        return minIncrement;
    }

    public int getLot() {
        return lot;
    }

    public int getPriority() {
        return priority;
    }

    public int getvATPercent() {
        return vATPercent;
    }

    public int getIsModified() {
        return isModified;
    }

    public int getItemid() {
        return itemid;
    }

    public int getiCarId() {
        return iCarId;
    }

    public String getiVinNumber() {
        return iVinNumber;
    }
}