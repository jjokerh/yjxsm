package com.yjxxt.controller;

import com.yjxxt.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;
}
