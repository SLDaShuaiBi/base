package com.vaneqi.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Resource {
    /**
     * ID
     */
    @Id
    @Column(name = "res_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resId;

    /**
     * 资源名
     */
    @Column(name = "res_name")
    private String resName;

    /**
     * 资源标识
     */
    @Column(name = "res_path")
    private String resPath;

    /**
     * 资源的父ID
     */
    @Column(name = "res_parent_id")
    private Long resParentId;

    /**
     * 资源类型 1功能，2按钮
     */
    @Column(name = "res_type")
    private Integer resType;

    /**
     * 获取ID
     *
     * @return res_id - ID
     */
    public Long getResId() {
        return resId;
    }

    /**
     * 设置ID
     *
     * @param resId ID
     */
    public void setResId(Long resId) {
        this.resId = resId;
    }

    /**
     * 获取资源名
     *
     * @return res_name - 资源名
     */
    public String getResName() {
        return resName;
    }

    /**
     * 设置资源名
     *
     * @param resName 资源名
     */
    public void setResName(String resName) {
        this.resName = resName;
    }

    /**
     * 获取资源标识
     *
     * @return res_path - 资源标识
     */
    public String getResPath() {
        return resPath;
    }

    /**
     * 设置资源标识
     *
     * @param resPath 资源标识
     */
    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    /**
     * 获取资源的父ID
     *
     * @return res_parent_id - 资源的父ID
     */
    public Long getResParentId() {
        return resParentId;
    }

    /**
     * 设置资源的父ID
     *
     * @param resParentId 资源的父ID
     */
    public void setResParentId(Long resParentId) {
        this.resParentId = resParentId;
    }

    /**
     * 获取资源类型 1功能，2按钮
     *
     * @return res_type - 资源类型 1功能，2按钮
     */
    public Integer getResType() {
        return resType;
    }

    /**
     * 设置资源类型 1功能，2按钮
     *
     * @param resType 资源类型 1功能，2按钮
     */
    public void setResType(Integer resType) {
        this.resType = resType;
    }
}