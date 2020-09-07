package com.vaneqi.entity;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    /**
     * ID
     */
    @Id
    @Column(name = "ur_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long urId;

    /**
     * 用户id
     */
    @Column(name = "u_id")
    private Long uId;

    /**
     * 角色id
     */
    @Column(name = "r_id")
    private Long rId;

    /**
     * 获取ID
     *
     * @return ur_id - ID
     */
    public Long getUrId() {
        return urId;
    }

    /**
     * 设置ID
     *
     * @param urId ID
     */
    public void setUrId(Long urId) {
        this.urId = urId;
    }

    /**
     * 获取用户id
     *
     * @return u_id - 用户id
     */
    public Long getuId() {
        return uId;
    }

    /**
     * 设置用户id
     *
     * @param uId 用户id
     */
    public void setuId(Long uId) {
        this.uId = uId;
    }

    /**
     * 获取角色id
     *
     * @return r_id - 角色id
     */
    public Long getrId() {
        return rId;
    }

    /**
     * 设置角色id
     *
     * @param rId 角色id
     */
    public void setrId(Long rId) {
        this.rId = rId;
    }
}