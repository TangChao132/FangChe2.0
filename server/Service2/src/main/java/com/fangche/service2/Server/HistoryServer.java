package com.fangche.service2.Server;

import com.fangche.service2.Pojo.dto.Result;

public interface HistoryServer {
    Result sumbit(Long uid, String type);

    Result search(Long uid);

    Result delete(Long uid);
}
