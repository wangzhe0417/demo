package com.fengling.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultBase<T> implements Serializable {
    private static final long serialVersionUID = -6296572417613770075L;

    public static final int   FAIL             = -1;
    public static final int   SUCCESS          = 0;

    private Integer           code             = -1;
    private String            msg              = "";
    private T                 data;
    private int               total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ResultBase() {

    }

    public ResultBase(T data, String msg) {
        this.code = SUCCESS;
        this.data = data;
        this.msg = msg;
    }

    public ResultBase(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultBase<T> getSuccessResult(T data) {
        return getSuccessResult(data, "success");
    }

    public static <T> ResultBase<T> getSuccessResult(T data, String msg) {
        return new ResultBase<>(data, msg);
    }

    public static <T> ResultBase<T> getErrorResult(String errorMessage) {
        return getErrorResult(FAIL, errorMessage);
    }

    public static <T> ResultBase<T> getErrorResult(Integer code, String errorMessage) {
        return new ResultBase<T>(code, errorMessage);
    }

    public static <T, K> ResultBase<T> getErrorResult(ResultBase<K> result) {
        return new ResultBase<>(result.getCode(), result.getMsg());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code == SUCCESS;
    }

    public boolean hasError() {
        return code != SUCCESS;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
