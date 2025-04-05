package com.yfan.demosecurity.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yfan.demosecurity.entity.User;
import lombok.Data;

/**
 * <p>
 * 在线用户 VO
 * </p>
 *
 * @author YondFane
 * @date Created in 2018-12-12 00:58
 */
@Data
public class OnlineUser {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 性别，男-1，女-0
     */
    private Integer sex;

    public static OnlineUser create(User user) {
        OnlineUser onlineUser = new OnlineUser();
        BeanUtil.copyProperties(user, onlineUser);
        onlineUser.setUsername(user.getName());
        return onlineUser;
    }
}
