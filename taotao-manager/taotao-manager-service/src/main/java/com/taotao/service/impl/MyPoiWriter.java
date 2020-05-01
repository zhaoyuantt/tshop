package com.taotao.service.impl;

import com.taotao.pojo.TbItem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 写入excel的class
 *
 * @author zhaoyuan
 * @date 2020, Jan 10 6:54 pm
 */
public class MyPoiWriter implements Runnable {


    Sheet sheet;
    Workbook workbook;

    List<TbItem> itemList;

    int noThread;

    // 写的第几行
    int rowNum;
    // 每个线程写的数量
    int everyThreadWriteNum;

    String filePath;
    OutputStream outputStream = null;

    CountDownLatch countDownLatch;

    public MyPoiWriter() {
    }

    public MyPoiWriter(Sheet sheet, Workbook workbook, List<TbItem> itemList, int rowNum, int everyThreadWriteNum, String filePath,CountDownLatch countDownLatch) {
        this.sheet = sheet;
        this.workbook = workbook;
        this.itemList = itemList;
        this.rowNum = rowNum;
        this.everyThreadWriteNum = everyThreadWriteNum;
        this.filePath = filePath;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        synchronized (workbook) {
            for (int i = 0; i < itemList.size(); i++) {
                Row row = sheet.createRow(((rowNum - 1) * everyThreadWriteNum) + 1);
                TbItem item = itemList.get(i);
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

            File file = new File(filePath);

            try {
                if (!file.exists()) {
                    file.createNewFile();
                }

                outputStream = new FileOutputStream(file);

                workbook.write(outputStream);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(null != outputStream){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            countDownLatch.countDown();
        }
    }
}
