package com.fangche.service2.Server.Imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fangche.service2.Mapper.HistoryMapper;
import com.fangche.service2.Pojo.dto.Result;
import com.fangche.service2.Pojo.entity.History;
import com.fangche.service2.Pojo.entity.Video;
import com.fangche.service2.Server.HistoryServer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@Service
public class HistoryServerImp implements HistoryServer {
    @Autowired
    private HistoryMapper historyMapper;
    @Override
    public Result sumbit(Long uid, String type) {
        History history = historyMapper.selectOne(new QueryWrapper<History>().eq("uid", uid).eq("type", type));
        if (history==null){
            //用户还没有该记录，执行插入操作
            history = new History();
            history.setType(type);
            history.setUid(uid);
            LocalDateTime now = LocalDateTime.now();
            history.setSubmitTime(now);
            historyMapper.insert(history);
        }else {
            //用户已有该记录，更新时间
            LocalDateTime now = LocalDateTime.now();
            history.setSubmitTime(now);
            historyMapper.updateById(history);
        }
        return Result.success("提交成功");
    }

    @Override
    public Result search(Long uid) {
        List<History> list = historyMapper.selectList(new QueryWrapper<History>().eq("uid", uid));
        HashMap<String, String> map = new HashMap<>();
        for(History history :list){
            map.put(history.getType(),history.getSubmitTime().toString());
        }

        return Result.success(map);
    }

    @Override
    public Result delete(Long uid) {
        int deleted = historyMapper.delete(new QueryWrapper<History>().eq("uid", uid));
        return Result.success("删除了"+deleted+"个记录");
    }
}
