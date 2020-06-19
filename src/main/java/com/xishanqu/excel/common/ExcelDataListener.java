package com.xishanqu.excel.common;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xishanqu.excel.service.ExcelService;
import com.xishanqu.excel.vo.ExcelUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc: 不能被spring管理，要每次读取excel都要new，然后里面到spring可以构造方法传进去
 * @author: BaoNing
 * @date: 2020/6/19 10:56
 */
public class ExcelDataListener extends AnalysisEventListener<ExcelUserVo> {

    private static final Logger log = LoggerFactory.getLogger(ExcelDataListener.class);

    private ExcelService excelService;

    /**
     * 每隔2000条存储数据库，实际使用可以是3000条，然后清理list，方便内存回收
     */
    private static final int BATCH_COUNT = 2000;
    private List<ExcelUserVo> list = new ArrayList<ExcelUserVo>();


    public ExcelDataListener(ExcelService excelService){
        this.excelService = excelService;
    }


    @Override
    public void invoke(ExcelUserVo data, AnalysisContext context) {
        list.add(data);
        //达到BATCH_COUNT，需要去存储一次数据库，防止数据几万条在内存，容易OOM
        if (list.size() >= BATCH_COUNT){
            saveExcelData();
            //存储完，清理list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了, 都会调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //保存数据，确保最后遗留的数据也存到数据库中
        saveExcelData();
        log.info("所有数据解析完成!");
    }


    /**
     * 加上存储数据库
     */
    private void saveExcelData(){
        log.info("{}条数据，开始存储数据库!", list.size());
        if (list.size() > 0){
            excelService.save(list);
        }
        log.info("存储数据库成功!");
    }


}
