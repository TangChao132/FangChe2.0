package com.fangche.service2.controller;


//import com.fangche.service1.utils.authority.Authority;
//import com.fangche.service1.utils.authority.Permission;

import com.fangche.service2.Pojo.dto.Result;
import com.fangche.service2.Server.Imp.VideoServerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    @Autowired
    private VideoServerImp videoServer;


    //videoList是根据条件查询，如：发布时间查询，视频名称查询，视频时长
    @GetMapping("/list")
//    @Authority
    public Result videoList(@RequestParam(value = "name",required = false) String name,
                            @RequestParam(value = "timeLength", defaultValue = "-1") Long timeLength,
                            @RequestParam(value = "days",required = false) String days) {
        return videoServer.videoList(name, timeLength, days);
    }


    @GetMapping("/searchByIds")
//    @Authority
    public Result searchByIds(@RequestParam("ids")List<Long> ids) {
        return videoServer.searchByIds(ids);
    }

    @GetMapping("/search")
    public Result searchById(@RequestParam("id") Long id){
        return videoServer.selectById(id);
    }

    @PostMapping("/list/click")
    public Result click(@RequestParam("id") Long id){
        return videoServer.videoClick(id);
    }


    @PostMapping("/add")
//    @Authority(Permission.AUTHOR)
    public Result videoAdd(@RequestParam("file") MultipartFile video, @RequestParam("name") String name) throws IOException {
        return videoServer.videoAdd(video, name);
    }

    @PutMapping("/put")
//    @Authority(Permission.AUTHOR)
    public Result videoAlterById(@RequestParam("id") Long id, @RequestParam("name") String name) {
        return  videoServer.updateById(id, name);
    }

    @DeleteMapping("/delete")
//    @Authority(Permission.AUTHOR)
    public Result deleteById(Long id) {
        return videoServer.deleteById(id);
    }

    @DeleteMapping("/deleteBach")
//    @Authority(Permission.AUTHOR)
    public Result deleteBach(ArrayList<Integer> list) {
        return videoServer.deleteBatchIds(list);
    }
}
