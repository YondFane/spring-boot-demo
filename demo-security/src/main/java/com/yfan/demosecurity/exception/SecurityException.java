package com.yfan.demosecurity.exception;

import com.yfan.demosecurity.common.BaseException;
import com.yfan.demosecurity.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 全局异常
 * </p>
 *
 * @author YondFane
 * @date Created in 2018-12-10 17:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityException extends BaseException {
    public SecurityException(Status status) {
        super(status);
    }

    public SecurityException(Status status, Object data) {
        super(status, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
