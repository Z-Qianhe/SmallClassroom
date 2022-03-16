package com.offcn.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult {
    //返回异步交互结果状态
    private boolean flag;
    //返回交互提示的信息
    private String message;
    //异步交互返回到前端的数据
    private Object data;
    //返回一个状态  1表示图片  2表示视频
    private Integer status;

    //重写构造函数
    public BaseResult(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public BaseResult(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
}
