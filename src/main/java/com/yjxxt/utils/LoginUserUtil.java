package com.yjxxt.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tony on 2016/8/23.
 */
public class LoginUserUtil {

    /**
     * 从cookie中获取userId
     * @param request
     * @return
     */
    public static int releaseUserIdFromCookie(HttpServletRequest request) {
        String userIdString = CookieUtil.getCookieValue(request, "userIdStr");
        if (StringUtils.isBlank(userIdString)) {
            return 0;
        }
        Integer userId = UserIDBase64.decoderUserID(userIdString);
        return userId;
    }

    /**
     * 从cookie中获取role
     *
     * @param request
     * @return
     */
    public static String releaseUserRoleFromCookie(HttpServletRequest request) {
        String role = CookieUtil.getCookieValue(request, "role");
        if (StringUtils.isBlank(role)) {
            return "";
        }
        return role;
    }
}
