package com.offcn.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.offcn.config.AlipayConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pay")
public class PayController {

    /**
     * 课程购付款的方法
     * @param wIDout_trade_no
     * @param wIDsubject
     * @param wIDtotal_amount
     * @param cid
     * @param uid
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping("/course")
    public String paySource(String wIDout_trade_no,String wIDsubject,
                            String wIDtotal_amount,Integer cid,Integer uid) throws AlipayApiException{
        //初始化支付的客户端类
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
                AlipayConfig.app_id, //支付宝账号id
                AlipayConfig.merchant_private_key, //私钥
                "json", //格式化格式
                AlipayConfig.charset, //编码
                AlipayConfig.alipay_public_key, //公钥
                AlipayConfig.sign_type); //签名方式
        //创建一个设置支付信息类 请求参数类
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //设置支付成功后跳转的地址 跳转到我们自己写的控制层中 将数据存放到数据库 支付成功后只能
        //使用重定向跳转
        alipayRequest.setReturnUrl("http://192.168.43.225:8080/course-user/buy?cid="+cid+"&uid="+uid);
        //设置支付失败后跳转的地址 最后自己定义一个支付失败的页面
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //拼接沙箱支付的json字符串
        alipayRequest.setBizContent("{\"out_trade_no\":\""+wIDout_trade_no+"\","
                +"\"subject\":\""+wIDsubject+"\","
                +"\"total_amount\":\""+wIDtotal_amount+"\","
                +"\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //封装请求 生成一个支付页面
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //返回结果
        return result;
    }


    @RequestMapping("/test")
    public String payTest(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException{
        //初始化支付的客户端类
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,////支付网关
                AlipayConfig.app_id,        //支付宝账号id
                AlipayConfig.merchant_private_key,  //私钥
                "json",             //格式化格式
                AlipayConfig.charset,       //编码
                AlipayConfig.alipay_public_key, //公钥
                AlipayConfig.sign_type);        // 加密类型
        //创建一个设置支付信息类  请求参数类
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //设置支付成功后跳转的地址
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        //设置支付失败后跳转的地址
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //从请求中获取订单号  必填  用来识别商户订单唯一的标识
        String out_trade_no = request.getParameter("wIDout_trade_no");
        //付款金额  必填
        String total_amount = request.getParameter("wIDtotal_amount");
        //订单名称  必填
        String subject = request.getParameter("wIDsubject");

        //将所有的订单支付信息，拼接到请求中
        alipayRequest.setBizContent("{\"out_trade_no\":\""+out_trade_no+"\","
                +"\"subject\":\""+subject+"\","
                +"\"total_amount\":\""+total_amount+"\","
                +"\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //封装请求  生成一个支付页面
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //返回结果
        return result;
    }
}
