package com.ohoyee.weight.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("E_WeightItem")
public class WeightItem implements Serializable {
    private static final long serialVersionUID = 493811964604845613L;
    private String ItemNM;
    private int BdNum;
    private String ProjectID;
    private String BatchNo;
    private String WagAbbProvince;
    private String WagonNumber;
    private String InfoCode;
    private String InfoName;
    private String InfoModel;
    private String InfoUnit;
    private String SupplierName;
    private float GrossWeight;
    private float TareWeight;
    private float Discount;
    private float NetWeight;
    private Date InTime;
    private int IsOut;
    private Date OutTime;
    private int IsBd;
    private String Maker;
    private String Remark;

    public String getItemNM() {
        return ItemNM;
    }

    public void setItemNM(String itemNM) {
        ItemNM = itemNM;
    }

    public int getBdNum() {
        return BdNum;
    }

    public void setBdNum(int bdNum) {
        BdNum = bdNum;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getWagAbbProvince() {
        return WagAbbProvince;
    }

    public void setWagAbbProvince(String wagAbbProvince) {
        WagAbbProvince = wagAbbProvince;
    }

    public String getWagonNumber() {
        return WagonNumber;
    }

    public void setWagonNumber(String wagonNumber) {
        WagonNumber = wagonNumber;
    }

    public String getInfoCode() {
        return InfoCode;
    }

    public void setInfoCode(String infoCode) {
        InfoCode = infoCode;
    }

    public String getInfoName() {
        return InfoName;
    }

    public void setInfoName(String infoName) {
        InfoName = infoName;
    }

    public String getInfoModel() {
        return InfoModel;
    }

    public void setInfoModel(String infoModel) {
        InfoModel = infoModel;
    }

    public String getInfoUnit() {
        return InfoUnit;
    }

    public void setInfoUnit(String infoUnit) {
        InfoUnit = infoUnit;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public float getGrossWeight() {
        return GrossWeight;
    }

    public void setGrossWeight(float grossWeight) {
        GrossWeight = grossWeight;
    }

    public float getTareWeight() {
        return TareWeight;
    }

    public void setTareWeight(float tareWeight) {
        TareWeight = tareWeight;
    }

    public float getDiscount() {
        return Discount;
    }

    public void setDiscount(float discount) {
        Discount = discount;
    }

    public float getNetWeight() {
        return NetWeight;
    }

    public void setNetWeight(float netWeight) {
        NetWeight = netWeight;
    }

    public Date getInTime() {
        return InTime;
    }

    public void setInTime(Date inTime) {
        InTime = inTime;
    }

    public int getIsOut() {
        return IsOut;
    }

    public void setIsOut(int isOut) {
        IsOut = isOut;
    }

    public Date getOutTime() {
        return OutTime;
    }

    public void setOutTime(Date outTime) {
        OutTime = outTime;
    }

    public int getIsBd() {
        return IsBd;
    }

    public void setIsBd(int isBd) {
        IsBd = isBd;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String maker) {
        Maker = maker;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public String toString() {
        return "WeightItem{" +
                ", BatchNo='" + BatchNo + '\'' +
                '}';
    }
}
