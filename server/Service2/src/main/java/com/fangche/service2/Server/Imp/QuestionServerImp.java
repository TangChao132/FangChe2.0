package com.fangche.service2.Server.Imp;

import com.fangche.service2.Pojo.dto.QuestionDto;
import com.fangche.service2.Pojo.dto.Result;
import com.fangche.service2.Pojo.entity.Question;
import com.fangche.service2.Server.QuestionServer;
import com.google.gson.Gson;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServerImp implements QuestionServer {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Result addFile(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请上传文件");
        }
        try {
            String str = new String(file.getBytes(), StandardCharsets.UTF_8);
            Gson gson = new Gson();
            QuestionDto[] questions = gson.fromJson(str, QuestionDto[].class);
            ArrayList<QuestionDto> list = new ArrayList<>(Arrays.asList(questions));
            mongoTemplate.insertAll(list);
        } catch (Exception e) {
            return Result.error("传入的不是有效的json文件");
        }
        return Result.success();
    }

    @Override
    public Result selectByType(String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(type));
        List<Question> questions = mongoTemplate.find(query, Question.class);
        return Result.success(questions);
    }

    @Override
    public Result deleteByType(String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(type));
        DeleteResult test = mongoTemplate.remove(query, "Question");
        System.out.println(test);
        return Result.success(test);
    }

    @Override
    public Result deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult test = mongoTemplate.remove(query, "Question");
        System.out.println(test);
        return Result.success(test);
    }
}
