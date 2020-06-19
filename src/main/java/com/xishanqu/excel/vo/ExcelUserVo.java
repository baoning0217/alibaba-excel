package com.xishanqu.excel.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.xishanqu.excel.common.SexConverter;
import lombok.Data;

import java.util.Date;

/**
 * @author captain
 */
@Data
public class ExcelUserVo {

    @ExcelProperty(index = 0, value = "编号")
    private Integer no;

    @ExcelProperty(index = 1, value = "姓名")
    private String name;

    @ExcelProperty(index = 2, value = "性别", converter = SexConverter.class)
    private Integer sex;

    @ExcelProperty(index = 3, value = "年龄")
    private Integer age;

    @ExcelProperty(index = 4, value = "籍贯")
    private String nativePlace;

    @ExcelProperty(index = 5, value = "手机号")
    private String mobile;

    @ExcelProperty(index = 6, value = "现住址")
    private String address;

    @ExcelProperty(index = 7, value = "职务")
    private String job;

    @ExcelProperty(index = 8, value = "入职时间")
    private Date entryTime;

}
