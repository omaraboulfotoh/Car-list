package com.omar.carlist.app.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omar.carlist.utils.LocaleHelper;

public class Car {

    @SerializedName("carID")
    @Expose
    private Integer carID;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("descriptionAr")
    @Expose
    private String descriptionAr;
    @SerializedName("descriptionEn")
    @Expose
    private String descriptionEn;
    @SerializedName("imgCount")
    @Expose
    private Integer imgCount;
    @SerializedName("sharingLink")
    @Expose
    private String sharingLink;
    @SerializedName("sharingMsgEn")
    @Expose
    private String sharingMsgEn;
    @SerializedName("sharingMsgAr")
    @Expose
    private String sharingMsgAr;
    @SerializedName("mileage")
    @Expose
    private String mileage;
    @SerializedName("makeID")
    @Expose
    private Integer makeID;
    @SerializedName("modelID")
    @Expose
    private Integer modelID;
    @SerializedName("bodyId")
    @Expose
    private Integer bodyId;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("makeEn")
    @Expose
    private String makeEn;
    @SerializedName("makeAr")
    @Expose
    private String makeAr;
    @SerializedName("modelEn")
    @Expose
    private String modelEn;
    @SerializedName("modelAr")
    @Expose
    private String modelAr;
    @SerializedName("bodyEn")
    @Expose
    private String bodyEn;
    @SerializedName("bodyAr")
    @Expose
    private String bodyAr;
    @SerializedName("AuctionInfo")
    @Expose
    private AuctionInfo auctionInfo;

    public Integer getCarID() {
        return carID;
    }

    public String getFullImage() {
        return image.replace("[w]", "0").replace("[h]", "0");
    }

    public String getThumbnailImage() {
        return image.replace("[w]", "200").replace("[h]", "200");
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public Integer getImgCount() {
        return imgCount;
    }

    public String getSharingLink() {
        return sharingLink;
    }

    public String getSharingMsgEn() {
        return sharingMsgEn;
    }

    public String getSharingMsgAr() {
        return sharingMsgAr;
    }

    public String getMileage() {
        return mileage;
    }

    public Integer getMakeID() {
        return makeID;
    }

    public Integer getModelID() {
        return modelID;
    }

    public Integer getBodyId() {
        return bodyId;
    }

    public Integer getYear() {
        return year;
    }

    public String getMakeEn() {
        return makeEn;
    }

    public String getMakeAr() {
        return makeAr;
    }

    public String getModel(String local) {
        return local.equals(LocaleHelper.LANGUAGE_ARABIC) ? modelAr : modelEn;
    }

    public String getBodyEn() {
        return bodyEn;
    }

    public String getBodyAr() {
        return bodyAr;
    }

    public AuctionInfo getAuctionInfo() {
        return auctionInfo;
    }
}
