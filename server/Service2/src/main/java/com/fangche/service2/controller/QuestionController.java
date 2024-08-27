package com.fangche.service2.controller;

//import com.fangche.service1.utils.authority.Authority;
//import com.fangche.service1.utils.authority.Permission;
import com.fangche.service2.Pojo.dto.Result;
import com.fangche.service2.Server.Imp.QuestionServerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RequestMapping("/api/question")
@RestController
public class QuestionController {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private QuestionServerImp questionServerImp;
    //添加题目
    @PostMapping("/add")
//    @Authority(Permission.AUTHOR)
    public Result add(@RequestParam("file")MultipartFile file) {
        return questionServerImp.addFile(file);
    }
    @GetMapping("/searchTypes")
//    @Authority
    public Result searchTypes(){
        List<String> types = mongoTemplate.findDistinct(new Query(), "type", "Question", String.class);
        return Result.success(types);
    }
    @GetMapping("/searchQuestion")
//    @Authority
    public Result searchByType(@RequestParam("type") String type){
        return questionServerImp.selectByType(type);
    }
    //删除套题
    @DeleteMapping("/deleteByType")
//    @Authority(Permission.AUTHOR)
    public Result DeleteQuestions(@RequestParam("type") String type){
        return questionServerImp.deleteByType(type);
    }
    //删除某一道题
    @DeleteMapping("/deleteById")
//    @Authority(Permission.AUTHOR)
    public Result DeleteQuestion(@RequestParam("id") String id){
        return questionServerImp.deleteById(id);
    }


}
