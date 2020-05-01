package com.taotao.service.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.concurrent.CountDownLatch;

public class ItemExportXTN {

    /**
     * sheet的row使用treeMap存储的，是非线程安全的，所以在创建row时需要进行同步操作。
     * @param sheet
     * @param rownum
     * @return
     */
    private static synchronized HSSFRow getRow(HSSFSheet sheet, int rownum) {
        return sheet.createRow(rownum);
    }

    protected static class PoiWriter implements Runnable{

        private final CountDownLatch doneSingal;
        private HSSFSheet sheet;
        private int start;
        private int end;

        public PoiWriter(CountDownLatch doneSingal, HSSFSheet sheet, int start, int end) {
            this.doneSingal = doneSingal;
            this.sheet = sheet;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int i = start;
            while (i<end){
                try {
                    HSSFRow row = getRow(sheet,i);
                    HSSFCell contentCell  = row.getCell(0);
                    if(null == contentCell){
                        contentCell = row.createCell(0);
                    }
                    contentCell.setCellValue(i+1);
                    ++i;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    doneSingal.countDown();
                    System.out.println("start: " + start + " end: " + end
                            + " Count: " + doneSingal.getCount());

                }
            }
        }
    }
}
