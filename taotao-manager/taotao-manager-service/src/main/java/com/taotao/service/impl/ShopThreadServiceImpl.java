package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ShopThreadService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 操作线程接口实现
 *
 * @author zhaoyuan
 * @date 2019, Dec 2 10:29 am
 */
@Service
public class ShopThreadServiceImpl implements ShopThreadService {

    private static final Logger log = Logger.getLogger(ShopThreadServiceImpl.class);

    @Resource(name = "threadPoolTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private TbItemMapper itemMapper;

    @Value("${DISPOSABLE_EXPORT_READ_ITEM_COUNT}")
    private String DISPOSABLE_EXPORT_READ_ITEM_COUNT;
    @Value("${ITEM_EXPORT_PATH}")
    private String ITEM_EXPORT_PATH;

    /**
     * 获得XSSFWorkbook对象
     *
     * @return
     */
    public XSSFWorkbook getXSSFWorkbook() {
        XSSFWorkbook workbook = null;
        FileInputStream fis = null;
        File file = new File("G:\\chromeDownload\\item.xlsx");
        try {
            if (null != file) {
                fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return workbook;
    }

    //Workbook workbook = getXSSFWorkbook();;
    Sheet sheet = null;
    public Sheet getSheet(){
        String sheetName = "iteminfo";
        sheet = getXSSFWorkbook().getSheet(sheetName);
        return sheet;
    }


    @Override
    public TaotaoResult itemDataExportExcel() {

        //查询一共有多少条商品数据
        TbItemExample itemExample = new TbItemExample();
        long itemCount = itemMapper.countByExample(itemExample);

        int readSize = Integer.valueOf(DISPOSABLE_EXPORT_READ_ITEM_COUNT);

        //应该启动的线程数
        long threadSize = itemCount / readSize;

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                log.info("开始执行线程池任务,当前线程名称：" + Thread.currentThread().getName());

                OutputStream outputStream = null;

                //标题行
                String[] title = {"商品id", "商品标题", "商品卖点", "商品价格，单位为：分", "库存数量", "商品条形码", "所属类目，叶子类目", "类目名称", "商品状态", "创建时间", "更新时间"};

                //创建Sheet对象
                String sheetName = "iteminfo";
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet(sheetName);

                if ("threadPoolTaskExecutor-1".equals(Thread.currentThread().getName())) {
                    //获取表头行
                    Row titleRow = sheet.createRow(0);

                    //设置单元格格式
                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

                    Cell cell = null;

                    //标题写入
                    for (int j = 0; j < title.length; j++) {
                        cell = titleRow.createCell(j);
                        cell.setCellValue(title[j]);
                        cell.setCellStyle(cellStyle);
                    }

                    //查询数据库
                    TbItemExample example = new TbItemExample();
                    PageHelper.startPage(1, 1000);
                    List<TbItem> itemList = itemMapper.selectByExample(example);

                    Row row = null;
                    for (int j = 0; j < itemList.size(); j++) {
                        TbItem item = itemList.get(j);
                        row = sheet.createRow(j + 1);
                        row.createCell(0).setCellValue(item.getId());//商品Id
                        row.createCell(1).setCellValue(item.getTitle());//商品标题
                        row.createCell(2).setCellValue(item.getSellPoint());//商品卖点
                        row.createCell(3).setCellValue(item.getPrice());//商品价格
                        row.createCell(4).setCellValue(item.getNum());//库存数量
                        row.createCell(5).setCellValue(item.getBarcode());//条形码
                        row.createCell(6).setCellValue(item.getCid());//叶子类目Id
                        row.createCell(7).setCellValue(item.getCname());//类目名称
                        row.createCell(8).setCellValue(item.getStatus());//状态
                        row.createCell(9).setCellValue(item.getCreated());//创建时间
                        row.createCell(10).setCellValue(item.getUpdated());//更新时间
                    }

                    //设置单元格宽度
                    for (int j = 0; j < title.length; j++) {
                        sheet.autoSizeColumn(j, true);
                        sheet.setColumnWidth(j, sheet.getColumnWidth(j) * 15 / 10);
                    }

                    String fileName = "G:\\chromeDownload\\item.xlsx";

                    try {
                        File file = new File(fileName);
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        outputStream = new FileOutputStream(fileName);

                        workbook.write(outputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (null != outputStream) {
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult exportDemo() {
        return null;
    }

    @Override
    public TaotaoResult itemDataBetchExportToExcel() {
        TbItemExample itemExample = new TbItemExample();
        //商品总数量
        long itemCount = itemMapper.countByExample(itemExample);
        //每次读取多少条记录
        int readSize = Integer.valueOf(DISPOSABLE_EXPORT_READ_ITEM_COUNT);

        //应该创建的线程数
        long runSize = itemCount/readSize;
        if(itemCount%readSize > 0){
            runSize++;
        }

        //创建两个计数器
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch((int)runSize-1);

        CountDownLatch firstBegin = new CountDownLatch(0);
        CountDownLatch firstEnd = new CountDownLatch(1);

        ExportThread exportFirstThread = new ExportThread(firstBegin,firstEnd,1,readSize,ITEM_EXPORT_PATH,itemMapper);
        Thread t = new Thread(exportFirstThread);
        t.start();
        try {
            firstEnd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //循环创建线程
        for (int i = 0; i < runSize-1; i++) {
            //计算每个线程应该读取的第几页的数据
            int pageIndex = i + 2;

            int excelRowsNum = ((i+1)*1000)+1;

            //创建线程类
            ExportBetchThread exportBetchThread = new ExportBetchThread(begin,end,pageIndex,readSize,ITEM_EXPORT_PATH,excelRowsNum,itemMapper,getSheet(),getXSSFWorkbook());

            synchronized (this){
                taskExecutor.execute(exportBetchThread);
            }

        }

        long startTime = System.currentTimeMillis();

        begin.countDown();
        try {
            //一直等待所有任务执行完毕
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        log.info("商品导出所有线程任务执行完毕，用时"+(endTime-startTime)+"毫秒");

        return TaotaoResult.ok("商品数据导出成功，一共用时："+(endTime-startTime)+"毫秒");
    }

    @Override
    public TaotaoResult itemDataExportGiveXTN() {
        CountDownLatch doneSingal = new CountDownLatch(4);

        HSSFWorkbook workbook;

        try {

            //File file = new File("G:\\chromeDownload\\item.xlsx");
            //if (!file.exists()) {
              //  file.createNewFile();
            //}

            FileInputStream fileInputStream = new FileInputStream("G:\\chromeDownload\\item.xlsx");
            workbook = new HSSFWorkbook(fileInputStream);

            HSSFSheet sheet = workbook.getSheetAt(0);

            taskExecutor.execute(new ItemExportXTN.PoiWriter(doneSingal,sheet,0,19999));
            taskExecutor.execute(new ItemExportXTN.PoiWriter(doneSingal,sheet,20000,39999));
            taskExecutor.execute(new ItemExportXTN.PoiWriter(doneSingal,sheet,40000,59999));

            /**
             * 使用CountDownLatch的await方法，等待所有线程完成sheet操作
             */
            doneSingal.await();

            //taskExecutor.shutdown();

            FileOutputStream fileOutputStream = new FileOutputStream("G:\\chromeDownload\\item.xlsx");

            workbook.write(fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult itemDataExportExcelWithNew() {
        long startTime = System.currentTimeMillis();;

        TbItemExample example = new TbItemExample();
        int itemCount = itemMapper.countByExample(example);

        //每个线程写入的数量
        Integer everyThreadWriteNum = Integer.valueOf(DISPOSABLE_EXPORT_READ_ITEM_COUNT);

        //应该开几个线程
        int threadSize = itemCount/everyThreadWriteNum;
        if(0 != itemCount%everyThreadWriteNum){
            threadSize++;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("iteminfo");

        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        //TODO 开始创建线程
        for (int i = 1;i <= threadSize;i++){
            // 查询商品数据
            TbItemExample exampleD = new TbItemExample();
            PageHelper.startPage(i, everyThreadWriteNum);
            List<TbItem> itemList = itemMapper.selectByExample(exampleD);

            // 创建写的class
            MyPoiWriter myPoiWriter = new MyPoiWriter(sheet,workbook,itemList,i,everyThreadWriteNum,ITEM_EXPORT_PATH,countDownLatch);

            Thread thread = new Thread(myPoiWriter);

            thread.start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        return TaotaoResult.ok("导入完成，用时"+(endTime-startTime)+"毫秒");
    }


}
