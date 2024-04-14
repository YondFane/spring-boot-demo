package com.yfan.demowebsocket.payload;

import com.google.common.collect.Lists;
import com.yfan.demowebsocket.model.Server;
import com.yfan.demowebsocket.payload.server.*;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 服务器信息VO
 * </p>
 *
 * @author YondFane
 * @date 2024-04-14 17:25
 */
@Data
public class ServerVO {
    List<CpuVO> cpu = Lists.newArrayList();
    List<JvmVO> jvm = Lists.newArrayList();
    List<MemVO> mem = Lists.newArrayList();
    List<SysFileVO> sysFile = Lists.newArrayList();
    List<SysVO> sys = Lists.newArrayList();

    public ServerVO create(Server server) {
        cpu.add(CpuVO.create(server.getCpu()));
        jvm.add(JvmVO.create(server.getJvm()));
        mem.add(MemVO.create(server.getMem()));
        sysFile.add(SysFileVO.create(server.getSysFiles()));
        sys.add(SysVO.create(server.getSys()));
        return null;
    }
}
