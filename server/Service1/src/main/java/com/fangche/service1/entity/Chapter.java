package com.fangche.service1.entity;

import lombok.Data;

@Data
public class Chapter {
    // 章节名
    private String name;
    // 章节内容, 可以是文本,文件url
    private String content;
}
