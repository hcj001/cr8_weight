package com.ohoyee.weight.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
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
}
