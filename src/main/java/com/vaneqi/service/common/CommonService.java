package com.vaneqi.service.common;

import com.github.pagehelper.PageInfo;
import com.vaneqi.common.PagedResult;
import org.apache.commons.lang3.time.DateUtils;

import java.util.List;

/**
 * @author qinlei
 * @Date 2020/7/8
 */
public class CommonService {
    /**
     * 用于构建分页对象
     *
     * @param list
     * @param page
     * @return
     */
    public PagedResult buildPageResult(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedResult grid = new PagedResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
