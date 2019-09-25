package com.bootdo.union.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncomeRecordVO {


    private Long id;
    //收入记录的id
    private String recordId;
    //收入公司id
    private String inCompanyId;
    //收入公司名称
    private String inCompanyName;
    //支出公司id
    private String outCompanyId;
    //支出公司名称
    private String outCompanyName;
    //收入总金额，两位小数
    private BigDecimal allAmount;
    //扎帐记录id
    private String settleAccountsId;
    //创建人
    private String creator;
    //创建时间
    private Date createTime;
    //修改人
    private String modifier;
    //修改时间
    private Date modifyTime;

    private List<UnionFundsIncomeDetailDO> incomeDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getInCompanyId() {
        return inCompanyId;
    }

    public void setInCompanyId(String inCompanyId) {
        this.inCompanyId = inCompanyId;
    }

    public String getInCompanyName() {
        return inCompanyName;
    }

    public void setInCompanyName(String inCompanyName) {
        this.inCompanyName = inCompanyName;
    }

    public String getOutCompanyId() {
        return outCompanyId;
    }

    public void setOutCompanyId(String outCompanyId) {
        this.outCompanyId = outCompanyId;
    }

    public String getOutCompanyName() {
        return outCompanyName;
    }

    public void setOutCompanyName(String outCompanyName) {
        this.outCompanyName = outCompanyName;
    }

    public BigDecimal getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(BigDecimal allAmount) {
        this.allAmount = allAmount;
    }

    public String getSettleAccountsId() {
        return settleAccountsId;
    }

    public void setSettleAccountsId(String settleAccountsId) {
        this.settleAccountsId = settleAccountsId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<UnionFundsIncomeDetailDO> getIncomeDetail() {
        return incomeDetail;
    }

    public void setIncomeDetail(List<UnionFundsIncomeDetailDO> incomeDetail) {
        this.incomeDetail = incomeDetail;
    }
}
