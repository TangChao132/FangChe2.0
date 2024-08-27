package com.fangche.service1.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    int baseAuthenticate(HttpServletRequest request);
    boolean isAuthorAuthenticate(HttpServletRequest request);
    boolean isAdminAuthenticate(HttpServletRequest request);
}
