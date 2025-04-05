package com.yfan.demosecurity.common;

/**
 * <p>
 * REST API 错误码接口
 * </p>
 *
 * @author YondFane
 * @date Created in 2025-04-06 14:35
 */
public interface IStatus {

    /**
     * 状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 返回信息
     *
     * @return 返回信息
     */
    String getMessage();

}
