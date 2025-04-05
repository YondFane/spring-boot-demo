package com.yfan.demosecurity.service;

import com.yfan.demosecurity.common.Status;
import com.yfan.demosecurity.entity.User;
import com.yfan.demosecurity.repository.UserRepository;
import com.yfan.demosecurity.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demosecurity.service
 * @Author: YANF
 * @CreateTime: 2025-03-27  12:57
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Primary
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查找用户
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // 根据用户信息构建UserDetails对象
        // 这里需要实现UserDetails接口，并重写相关方法
        // 例如：getAuthorities()用于返回用户的权限集合
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setUsername(user.getName());
        userPrincipal.setId(user.getId());
        userPrincipal.setPassword(user.getPassword());
        // 默认启用
        userPrincipal.setStatus(1);
        return userPrincipal;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
