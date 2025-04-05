package com.yfan.demosecurity.controller;

import com.yfan.demosecurity.common.ApiResponse;
import com.yfan.demosecurity.common.Status;
import com.yfan.demosecurity.request.LoginRequest;
import com.yfan.demosecurity.service.UserService;
import com.yfan.demosecurity.util.JwtUtil;
import com.yfan.demosecurity.vo.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demosecurity.controller
 * @Author: YANF
 * @CreateTime: 2025-03-27  13:06
 * @Description: 用户控制层
 * @Version: 1.0
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @PostMapping(value = "/login")
    @ResponseBody
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication, loginRequest.getRememberMe());
        return ApiResponse.ofSuccess(new JwtResponse(jwt));
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request) {
        // 设置JWT过期
        jwtUtil.invalidateJWT(request);

        return ApiResponse.ofStatus(Status.LOGOUT);
    }
}
