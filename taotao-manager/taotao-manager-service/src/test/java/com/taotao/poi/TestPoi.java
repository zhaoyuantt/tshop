package com.taotao.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * 测试poi Excel写入线程池方式
 * @author zhaoyuan
 * @date 2019,Dec 8 12:30 pm
 */
public class TestPoi {
    public static void main(String[] args) {
        multiThreadWrite();
    }

    public static void multiThreadWrite(){
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        ThreadPoolTaskExecutor taskExecutor = applicationContext.getBean(ThreadPoolTaskExecutor.class);

        CountDownLatch doneSingal = new CountDownLatch(4);

        HSSFWorkbook workbook;

        try {

            File file = new File("G:\\chromeDownload\\item.xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new HSSFWorkbook(fileInputStream);

            HSSFSheet sheet = workbook.getSheetAt(0);

            taskExecutor.execute(new PoiWriter(doneSingal,sheet,0,19999));
            taskExecutor.execute(new PoiWriter(doneSingal,sheet,20000,39999));
            taskExecutor.execute(new PoiWriter(doneSingal,sheet,40000,59999));

            /**
             * 使用CountDownLatch的await方法，等待所有线程完成sheet操作
             */
            doneSingal.await();

            taskExecutor.shutdown();

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

    }

    /**
     * sheet的row使用treeMap存储的，是非线程安全的，所以在创建row时需要进行同步操作。
     * @param sheet
     * @param rownum
     * @return
     */
    private static synchronized HSSFRow getRow(HSSFSheet sheet, int rownum) {
        return sheet.createRow(rownum);
    }


    /**
     * 进行Sheet写操作的sheet
     */
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
