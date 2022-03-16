package com.offcn.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000119612569";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgx8Fq3GG0/yL9IljAc2ExqtFOpoys/7UIeBfWBs71rrMn0z4z9BuBd0sGB8iOsdQUrObbXMm6EvV4r6HzawqB1S+F+ui3IxkvPvhTLXCucXU/Y2jVnSkEIUHJrx+dB0rg/Es0154A8UgmNSXoklTRrerGvfZS71CflFmcNtcvVpNxU0dALaFVruXRUxYUOclhB9Wase76b/LEwN9fgZWNt7hX7a7XXnEcZkfjyYk8i2zltl5oki/yctvN+BZ4me3OLLM/nVgPBHoNuyiHxVWpnX3XbXLPmhNVut11z2NagsEUqUu3yrPRrwex3ryf3tbFPI7FkX1EB3FaDO5ySjExAgMBAAECggEAM85Jr2IVhZmUL5ZqnJBQ6A0PZlVaDMBUF8eCfq8w7o79IT760wi7+Cp1HRtS70+zc3oG5PXv9in3uIx3FZUPDKihqH0hkSkZC1Ux9LM2lK7wRZ2FgRcjyTXiGdd4y0M3SGli3kL/oXQdeFo1vcuw0uNEPAenoC+GffTyLtio25J2t5xYs/97+JdgixDaCnirWWN4KJvieT/SL6NQfnUgEQ9baHoutHhRtGT37vClcEqQTw/6Z+Oi3UgY6f7HGLlqNs/nMZomOA0aJdN6y4kR5EWV46as9GVbXl7EawaY+JAL044Q/dl7CsYQCtKHMtK9UyWr7fpBvAFoPIFZftR1JQKBgQD/TbGB1Hrfp/iSWGOs+kkTQ+QDTx+7mQnZip6fjZVkWVM2jfPpxfpGfSvlXw/xtB4WJIHshu7XjCKQAYvCfgteSRDyMN8BR/CcG5FdSw78j/oX6YoMeJ9Vol8HPWwUIWHo96EuGRRri3GPmok2XNqZizU7HdhK7Pc4psabZ3mHEwKBgQChOAvRnLicwE34YBQRqrQo1RuPIhkXcpXn905c8SsE3mx6a5RPjWu0cGB7tgWcOaoUYtxXgOWD6O02hYtJ9Ygi3xh05St4x7Elw1lYOgjmab2njD5KSVND+kCDp0nQoi94y60NwIeAUMxEcudgDuOBZTLMdubwoW+T2ovAnoibKwKBgHYu/xb8x4y1cyo75Jh0nFNgwDKP9D6hj5+2vctafWeguhatC9aSIVN0KlfZ88WN/cd+Su61X+dEJdPIA2ybcKPK6x9SZElxhf6d/GLVljBwbfOX1qIDTK8VpSvy0Q9YjLyNtX1jesJ2ILG7df1GaqNdf9zH6hJ1M9Rzz8is0+MlAoGAYydoRXQBKw8vJSpaTX9SrKZc+8w56Gzl+7Pjc1A0aBswVp6hqcfNzT0sagnKK4pfq4geAodZIab5dgXuY/9pyiCyFvr3oKIYtqQN6DBRVv0D0u9zRBcS6gUY9p/Fy1ZkogOKnKGj9Ow8pYCSA0dE70lBU/VxFeAEEshgGYindd8CgYEAkHn45KCa8SxWfKHbONtBcfGwdiFQQJf8r5mZRZRFa+R9VwsniFjiSzJdcJwRpuOJZMGH2hPjQrZKlQKWpuLHmz5CSVP6yok38JlMmMSMHCYkqn4Q4z4+jnUXv7rVy2VNCkYg6a7EAEXdMLpSWsNnGBkuTfxstR1NfMldP264dAE=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoMfBatxhtP8i/SJYwHNhMarRTqaMrP+1CHgX1gbO9a6zJ9M+M/QbgXdLBgfIjrHUFKzm21zJuhL1eK+h82sKgdUvhfrotyMZLz74Uy1wrnF1P2No1Z0pBCFBya8fnQdK4PxLNNeeAPFIJjUl6JJU0a3qxr32Uu9Qn5RZnDbXL1aTcVNHQC2hVa7l0VMWFDnJYQfVmrHu+m/yxMDfX4GVjbe4V+2u115xHGZH48mJPIts5bZeaJIv8nLbzfgWeJntziyzP51YDwR6Dbsoh8VVqZ19121yz5oTVbrddc9jWoLBFKlLt8qz0a8Hsd68n97WxTyOxZF9RAdxWgzuckoxMQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
