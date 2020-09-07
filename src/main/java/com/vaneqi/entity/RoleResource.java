package com.vaneqi.entity;

import javax.persistence.*;

@Table(name = "role_resource")
public class RoleResource {
    /**
     * ID
     */
    @Id
    @Column(name = "rr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rrId;

    /**
     * 角色id
     */
    @Column(name = "r_id")
    private Long rId;

    /**
     * 资源id
     */
    @Column(name = "res_id")
    private Long resId;

    /**
     * 获取ID
     *
     * @return rr_id - ID
     */
    public Long getRrId() {
        return rrId;
    }

    /**
     * 设置ID
     *
     * @param rrId ID
     */
    public void setRrId(Long rrId) {
        this.rrId = rrId;
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

    /**
     * 获取资源id
     *
     * @return res_id - 资源id
     */
    public Long getResId() {
        return resId;
    }

    /**
     * 设置资源id
     *
     * @param resId 资源id
     */
    public void setResId(Long resId) {
        this.resId = resId;
    }
}