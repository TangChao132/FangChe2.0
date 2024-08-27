package com.fangche.service1.service;

import com.fangche.service1.entity.Response;
import com.fangche.service1.entity.requestParam.course.CourseAddParam;
import com.fangche.service1.entity.requestParam.course.CourseSearchParam;
import com.fangche.service1.entity.requestParam.course.CourseSetImageParam;
import com.fangche.service1.entity.requestParam.course.CourseUpdateParam;
import jakarta.servlet.http.HttpServletRequest;

public interface CourseService {
    Response addCourse(CourseAddParam param);
    Response updateCourse(CourseUpdateParam param);
    Response setCourseImage(CourseSetImageParam param);
    Response searchCourse(CourseSearchParam param);
    Response deleteCourse(Long cid);
    Response collectCourse(Long cid, HttpServletRequest request);
    Response removeCollectCourse(Long cid, HttpServletRequest request);
    Response getCollectCourses(Long id);
}
