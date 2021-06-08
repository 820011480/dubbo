package com.alibaba.dubbo.demo.provider.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author: mady
 * @Date: 2021/6/8 9:03
 * @Desc:  inner接口描述
 */
//dubbo 扩展标识
@SPI
public interface InnerSpi {

    /**
     * 打印方法描述
     * @param url
     * @param desc
     */
    @Adaptive(value = "print")
    void testPrint(URL url, String desc);


    /**
     * 工作方式
     * @param type
     * @param desc
     */
//    @Adaptive(value = "work")
//    void work(String type, String desc);
}
