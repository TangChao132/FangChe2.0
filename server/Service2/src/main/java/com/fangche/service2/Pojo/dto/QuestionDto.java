package com.fangche.service2.Pojo.dto;

import com.fangche.service2.Pojo.entity.Option;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "Question")
public class QuestionDto {
    private String text; // 题目文本
    private List<Option> options; // 选项列表
    private String correctAnswer; // 正确答案
    private String type;//表示属于哪一套题(属于哪套卷子)

}
