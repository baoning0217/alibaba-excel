package com.xishanqu.excel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xishanqu.excel.common.QueryParam;
import com.xishanqu.excel.mapper.ExcelMapper;
import com.xishanqu.excel.vo.ExcelUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @desc:
 * @author: BaoNing
 * @date: 2020/6/19 11:02
 */
@Service
public class ExcelService {

    @Resource
    private ExcelMapper excelMapper;


    /**
     * @Desc 批量插入
     * @Params: list
     * @Return: void
     * @Author BaoNing
     * @Date 2020/6/19 11:51
    */
    public void save(List<ExcelUserVo> list){
        excelMapper.batchInsert(list);
    }


    /**
     * @Desc: 分页查询
     * @Params: queryParam
     * @Return: PageInfo
     * @Author BaoNing
     * @Date 2020/6/19 17:51
    */
    public PageInfo<ExcelUserVo> listExcelUserVo(QueryParam queryParam){
        PageInfo<ExcelUserVo> pageInfo;
        PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize(), true);
        Long count = excelMapper.countExcelUserVo(queryParam);
        if (count == 0){
            return new PageInfo<>();
        }
        List<ExcelUserVo> excelUserVos = excelMapper.listExcelUserVo(queryParam);
        pageInfo = new PageInfo<>(excelUserVos);
        return pageInfo;
    }


}
