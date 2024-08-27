package com.fangche.service1.controller;

import com.fangche.service1.entity.Response;
import com.fangche.service1.entity.requestParam.course.CourseAddParam;
import com.fangche.service1.entity.requestParam.course.CourseSearchParam;
import com.fangche.service1.entity.requestParam.course.CourseSetImageParam;
import com.fangche.service1.entity.requestParam.course.CourseUpdateParam;
import com.fangche.service1.service.CourseService;
import com.fangche.service1.utils.authority.Authority;
import com.fangche.service1.utils.authority.Permission;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/add")
    @Authority(Permission.AUTHOR)
    public Response addCourse(@Valid @RequestBody CourseAddParam param) {
        return courseService.addCourse(param);
    }
    @PostMapping("/set-image")
    @Authority(Permission.AUTHOR)
    public Response setCourseImage(MultipartFile file,
                                   String id
                                   ) {
        CourseSetImageParam param = new CourseSetImageParam();
        param.setId(Long.valueOf(id));
        param.setFile(file);
        // 调用service层的方法
        return courseService.setCourseImage(param);
    }

    @PutMapping("/update")
    @Authority(Permission.AUTHOR)
    public Response updateCourse(@Valid @RequestBody CourseUpdateParam param) {
        return courseService.updateCourse(param);
    }
    @GetMapping("/search")
    public Response searchCourse(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "page_size", required = false, defaultValue = "10") int page_size,
                                 @RequestParam(value = "category", required = false, defaultValue = "") String category,
                                 @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                 @RequestParam(value = "teacher", required = false, defaultValue = "") String teacher,
                                 @RequestParam(value = "id", required = false, defaultValue = "") Long id) {
        CourseSearchParam param = new CourseSearchParam();
        param.setKeyword(keyword);
        param.setPage(page);
        param.setPageSize(page_size);
        param.setCategory(category);
        param.setName(name);
        param.setTeacher(teacher);
        param.setId(id);
        return courseService.searchCourse(param);
    }
    @DeleteMapping("/del/{id}")
    @Authority(Permission.AUTHOR)
    public Response delCourse(@PathVariable("id") Long id) {
        return courseService.deleteCourse(id);
    }

    /**
     *  收藏课程
     * @param id 课程id
     * @param request HttpServletRequest
     * @return Response
     */
    @PostMapping("/collect/{id}")
    @Authority
    public Response collectCourse(@PathVariable("id") Long id, HttpServletRequest request) {
        return courseService.collectCourse(id, request);
    }
    /**
     *  取消收藏课程
     * @param id 课程id
     * @param request HttpServletRequest
     * @return Response
     */
    @DeleteMapping("/collect/remove/{id}")
    @Authority
    public Response removeCollectCourse(@PathVariable("id") Long id, HttpServletRequest request) {
        return courseService.removeCollectCourse(id, request);
    }

    @GetMapping("/collect/get/{id}")
    @Authority
    public Response getCollectCourse(@PathVariable("id") Long id) {
        return courseService.getCollectCourses(id);
    }
}
