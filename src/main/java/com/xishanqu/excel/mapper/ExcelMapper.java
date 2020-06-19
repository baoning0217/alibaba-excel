package com.xishanqu.excel.mapper;

import com.xishanqu.excel.common.QueryParam;
import com.xishanqu.excel.vo.ExcelUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @desc:
 * @author: BaoNing
 * @date: 2020/6/19 11:11
 */
@Mapper
public interface ExcelMapper {

    /**
     * 批量插入
     * @param list
     */
    void batchInsert(List<ExcelUserVo> list);


    /**
     * 统计数量
     * @param queryParam
     * @return
     */
    Long countExcelUserVo(QueryParam queryParam);


    /**
     * 批量查询
     * @param queryParam
     * @return
     */
    List<ExcelUserVo> listExcelUserVo(QueryParam queryParam);

}
