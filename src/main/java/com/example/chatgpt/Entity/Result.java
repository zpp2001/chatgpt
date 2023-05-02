package com.example.chatgpt.Entity;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KSJ
 * @version 1.0
 * @description: 统一返回结果
 * @date 2022/10/1 17:01
 */


@Data
public class Result {


    private Boolean success;
    private Integer code;
    private String message;


    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private Result() {}

    //成功静态方法
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    //这块返回this做个说明，
    //谁调用返回谁，这是为了可以实现 链式编程
    //随后可以Result.ok().code(20000).data(...)
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}