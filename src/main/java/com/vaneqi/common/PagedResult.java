package com.vaneqi.common;

import lombok.Data;

import java.util.List;

/**
 * @author qinlei
 * @Date 2020/7/8
 */
@Data
public class PagedResult<T> {
    /**
     * 当前页数
     */
    private int page;
    /**
     * 总页数
     */
    private int total;
    /**
     * 总记录数
     */
    private long records;
    /**
     * 每行显示的内容
     */
    private List<T> rows;
}
