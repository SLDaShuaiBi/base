package com.vaneqi.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
    /**
     * ID
     */
    @Id
    @Column(name = "u_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uId;

    /**
     * 账号
     */
    @Column(name = "u_account")
    private String uAccount;

    /**
     * 加密密码
     */
    @Column(name = "u_password")
    private String uPassword;

    /**
     * 是否被禁用
     */
    @Column(name = "u_is_disable")
    private String uIsDisable;

    /**
     * 获取ID
     *
     * @return u_id - ID
     */
    public Long getuId() {
        return uId;
    }

    /**
     * 设置ID
     *
     * @param uId ID
     */
    public void setuId(Long uId) {
        this.uId = uId;
    }

    /**
     * 获取账号
     *
     * @return u_account - 账号
     */
    public String getuAccount() {
        return uAccount;
    }

    /**
     * 设置账号
     *
     * @param uAccount 账号
     */
    public void setuAccount(String uAccount) {
        this.uAccount = uAccount;
    }

    /**
     * 获取加密密码
     *
     * @return u_password - 加密密码
     */
    public String getuPassword() {
        return uPassword;
    }

    /**
     * 设置加密密码
     *
     * @param uPassword 加密密码
     */
    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    /**
     * 获取是否被禁用
     *
     * @return u_is_disable - 是否被禁用
     */
    public String getuIsDisable() {
        return uIsDisable;
    }

    /**
     * 设置是否被禁用
     *
     * @param uIsDisable 是否被禁用
     */
    public void setuIsDisable(String uIsDisable) {
        this.uIsDisable = uIsDisable;
    }
}