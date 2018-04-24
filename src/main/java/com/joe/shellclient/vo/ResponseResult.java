package com.joe.shellclient.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joe.shellclient.enums.ResponseCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Joe
 * @description: http返回对象
 * @date: Create in 10:37 2018/4/24
 */
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;

    @JSONField(serialize = false)
    public boolean isSuccess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
}
