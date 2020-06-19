package com.xishanqu.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.xishanqu.excel.common.ExcelDataListener;
import com.xishanqu.excel.common.QueryParam;
import com.xishanqu.excel.service.ExcelService;
import com.xishanqu.excel.util.ExcelFileUtil;
import com.xishanqu.excel.vo.ExcelUserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @desc:
 * @author: BaoNing
 * @date: 2020/6/19 13:58
 */
@RestController
@RequestMapping("/api/excel")
public class ExcelController {


    @Resource
    private ExcelService excelService;

    /**
     * @Desc: read excel
     * @Params:
     * @Return:
     * @Author BaoNing
     * @Date 2020/6/19 14:02
    */
    @GetMapping("/read")
    public String readExcel(@RequestParam(value = "bool", defaultValue = "false") Boolean bool){
        InputStream inputStream = ExcelFileUtil.getResourcesFileInputStream("user.xlsx");
        EasyExcel.read(inputStream, ExcelUserVo.class, new ExcelDataListener(excelService)).sheet().doRead();
        return "OK";
    }



    /**
     * @Desc: write excel
     * @Params:
     * @Return:
     * @Author BaoNing
     * @Date 2020/6/19 14:02
     */
    @GetMapping("/write")
    public String writeExcel(@RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        QueryParam queryParam = new QueryParam();
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        PageInfo<ExcelUserVo> pageInfo = excelService.listExcelUserVo(queryParam);
        List<ExcelUserVo> list = pageInfo.getList();
        String fileName = "user.xlsx";
        EasyExcel.write(fileName, ExcelUserVo.class).sheet("模板").doWrite(list);
        return "OK";
    }




}
