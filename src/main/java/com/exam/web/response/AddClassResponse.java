package com.exam.web.response;

/**
 * Created by uio5 on 2016/12/24.
 */
public class AddClassResponse {

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    boolean success = false;
    long class_id;
}
