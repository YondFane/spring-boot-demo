package com.yfan.demowebsocket.controller;

import cn.hutool.core.lang.Dict;
import com.yfan.demowebsocket.model.Server;
import com.yfan.demowebsocket.payload.ServerVO;
import com.yfan.demowebsocket.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YondFane
 * @CreateTime: 2024-04-14  16:00
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/server")
public class WebSocketController {

    @GetMapping
    public Dict serverInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        return ServerUtil.wrapServerDict(serverVO);
    }

}
