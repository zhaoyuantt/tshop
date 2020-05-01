package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 操作线程的接口
 * @author zhaoyuan
 * @date 2019,Dec 2 10:26 am
 */
public interface ShopThreadService {

    /**
     *
     * @return
     */
    public TaotaoResult itemDataExportExcel();

    /**
     * https://blog.csdn.net/zknxx/article/details/78572108
     * https://www.cnblogs.com/gabriel-y/p/11761482.html
     * @return
     */
    public TaotaoResult exportDemo();

    /**
     * 商品数据使用线程池批量导出
     * @date 2019,Dec 13 9:18 am
     * @return
     */
    public TaotaoResult itemDataBetchExportToExcel();

    public TaotaoResult itemDataExportGiveXTN();

    public TaotaoResult itemDataExportExcelWithNew();
}
