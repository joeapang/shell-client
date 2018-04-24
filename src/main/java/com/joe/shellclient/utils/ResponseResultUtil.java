package com.joe.shellclient.utils;

import com.joe.shellclient.enums.ResponseCode;
import com.joe.shellclient.vo.ResponseResult;

/**
 * @author: Joe
 * @description:
 * @date: Create in 10:40 2018/4/24
 */
public class ResponseResultUtil {
    public static ResponseResult success(Object data, String msg) {

        return new ResponseResult(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static ResponseResult success() {
        return new ResponseResult(null, ResponseCode.SUCCESS.getMsg());
    }

    public static ResponseResult error(Integer code, String msg) {
        return new ResponseResult(code, msg);
    }
}
