package com.river.condition;

import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: he.feng
 * \* Date: 2017/9/29
 * \* Description:
 * \
 */
public class BaseCondition {

    private static final int DEFAULT_PAGE_NO = 1;
    private static final int DEFAULT_PAGE_LIMIT = 10;

    /** 默认第一页*/
    private Integer pageNo = DEFAULT_PAGE_NO;

    /** 默认每页十条*/
    private Integer pageLimit = DEFAULT_PAGE_LIMIT;


    public PageRowBounds getPageRowBounds() {
        return new PageRowBounds(getOffset(),pageLimit);
    }

    public int getOffset() {
        if(pageNo >=1) {
            return (pageNo - 1) * pageLimit;
        }
        return RowBounds.NO_ROW_OFFSET;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }
}
