package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 商品数据导出线程类，这个类并行写入
 *
 * @author zhaoyuan
 * @date 2019, Dec 4 11:04 am
 */

public class ExportBetchThread implements Runnable {

    private static final Logger log = Logger.getLogger(ShopThreadServiceImpl.class);

    private CountDownLatch begin;
    private CountDownLatch end;

    //使用PageHelper分段读取数据，pageIndex:要读第几页数据 readSize:每次读取多少条数据
    private int pageIndex;
    private int readSize;

    private String outPath;

    private int excelRowsNum;

    Sheet sheet;
    Workbook workbook;

    TbItemMapper itemMapper;

    public ExportBetchThread() {
    }

    public ExportBetchThread(CountDownLatch begin, CountDownLatch end, int pageIndex, int readSize, String outPath, int excelRowsNum, TbItemMapper itemMapper, Sheet sheet, Workbook workbook) {
        this.begin = begin;
        this.end = end;
        this.pageIndex = pageIndex;
        this.readSize = readSize;
        this.outPath = outPath;
        this.excelRowsNum = excelRowsNum;
        this.itemMapper = itemMapper;
        this.sheet = sheet;
        this.workbook = workbook;
    }



    OutputStream outputStream = null;

    @Override
    public void run() {
        //标题行
        String[] title = {"商品id", "商品标题", "商品卖点", "商品价格，单位为：分", "库存数量", "商品条形码", "所属类目，叶子类目", "类目名称", "商品状态", "创建时间", "更新时间"};

        //查询数据库
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(pageIndex, readSize);
        List<TbItem> itemList = itemMapper.selectByExample(example);

        //sheet = getSheet();
        int lastRowNum = excelRowsNum;

        Row row ;
        for (int j = 0; j < itemList.size(); j++) {
            TbItem item = itemList.get(j);
            row = sheet.createRow(lastRowNum + j);
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

        String fileName = outPath;

        try {

            outputStream = new FileOutputStream(fileName);
            synchronized (this) {
                workbook.write(outputStream);
            }

            try {
                //等待
                System.out.println("**********begin.getCount()********** " + begin.getCount());
                begin.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        end.countDown();
        System.out.println("**********end.getCount()********** " + end.getCount());
    }

}
