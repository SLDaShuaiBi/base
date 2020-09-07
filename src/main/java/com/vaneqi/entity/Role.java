package com.vaneqi.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Role {
    /**
     * ID
     */
    @Id
    @Column(name = "r_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rId;

    /**
     * 英文名
     */
    @Column(name = "r_name_e")
    private String rNameE;

    /**
     * 中文名
     */
    @Column(name = "r_name")
    private String rName;

    /**
     * 获取ID
     *
     * @return r_id - ID
     */
    public Long getrId() {
        return rId;
    }

    /**
     * 设置ID
     *
     * @param rId ID
     */
    public void setrId(Long rId) {
        this.rId = rId;
    }

    /**
     * 获取英文名
     *
     * @return r_name_e - 英文名
     */
    public String getrNameE() {
        return rNameE;
    }

    /**
     * 设置英文名
     *
     * @param rNameE 英文名
     */
    public void setrNameE(String rNameE) {
        this.rNameE = rNameE;
    }

    /**
     * 获取中文名
     *
     * @return r_name - 中文名
     */
    public String getrName() {
        return rName;
    }

    /**
     * 设置中文名
     *
     * @param rName 中文名
     */
    public void setrName(String rName) {
        this.rName = rName;
    }
}