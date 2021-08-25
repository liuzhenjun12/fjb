package com.fjb.web.controller.drivers;

import com.fjb.common.constant.Constants;
import com.fjb.common.core.domain.AjaxResult;
import com.fjb.common.core.domain.entity.SysDictData;
import com.fjb.common.core.domain.entity.SysUser;
import com.fjb.common.core.domain.model.LoginBody;
import com.fjb.common.core.domain.model.LoginUser;
import com.fjb.common.utils.ServletUtils;
import com.fjb.framework.web.service.SysLoginService;
import com.fjb.framework.web.service.TokenService;
import com.fjb.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备登陆
 *
 */
@RestController
@RequestMapping("/drivers/v1")
public class LoginController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysLoginService loginService;
    /**
     * 登录方法
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        return ajax;
    }
}
