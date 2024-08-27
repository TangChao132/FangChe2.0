package com.fangche.service2.controller;

//import com.fangche.service1.utils.authority.Authority;
import com.fangche.service2.Pojo.dto.Result;
import com.fangche.service2.Server.Imp.HistoryServerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/history")
@RestController
public class HistoryController {
    @Autowired
    private HistoryServerImp historyServer;
    @PostMapping("/submit")
    public Result submitTest(@RequestParam("uid") Long uid,@RequestParam("type") String type){
        return historyServer.sumbit(uid,type);
    }

    @GetMapping("search")
    public Result search(@RequestParam("uid") Long uid){
        return historyServer.search(uid);
    }
    @DeleteMapping("/delete")
    public Result deleted(@RequestParam("uid") Long uid){
        return historyServer.delete(uid);
    }
}
