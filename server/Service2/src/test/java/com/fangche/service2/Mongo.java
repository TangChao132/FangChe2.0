package com.fangche.service2;

import com.fangche.service2.Pojo.entity.Question;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
public class Mongo {
    @Autowired
    MongoTemplate mongoTemplate;


//    集合的操作
    @Test
    void mongotest(){

        if (mongoTemplate.collectionExists("test")){
            mongoTemplate.dropCollection("test");
        }
        mongoTemplate.createCollection("question");

    }
    @Test
    void test(){
        MongoCollection<Document> test = mongoTemplate.getCollection("test");
        List<Question> questions = mongoTemplate.find(new Query(), Question.class);
        for (Question q:questions){
            System.out.println(q);
        }

    }
    @Test
    void test1(){
        List<String> distinct = mongoTemplate.findDistinct(new Query(), "type", "Question", String.class);
        for (String q:distinct){
            System.out.println(q);
        }
    }
    @Test
    void test2(){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("数学"));
        List<Question> questions = mongoTemplate.find(query, Question.class);
        for (Question q:questions){
            System.out.println(q);
        }
    }

    @Test
    void test3(){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("计算机系统结构"));
        DeleteResult test = mongoTemplate.remove(query, "Question");
        System.out.println(test);
    }
}
