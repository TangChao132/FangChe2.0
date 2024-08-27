import {createRouter,createWebHashHistory} from "vue-router";
import main from "../views/main.vue";
import course from  "../views/course.vue"

const routes=[
    {
        path:"/",
        name:"main",
        component:main,
    },
    {
        path: "/courseDetail",
        name:"CourseDetail",
        component:course
    },
    {
        path: "/user",
        name:"user",
        component: ()=>import("../views/userCenter.vue"),
        children: [
            {
                path: 'one',
                name:'myClass',
                component: ()=>import('../components/one.vue'),
            },
            {
                path: 'two',
                name: 'myTest',
                component:()=>import('../components/two.vue'),
            },
            {
                path: 'three',
                name: 'personSet',
                component: () => import('../components/three.vue')
            },
            {
                path: 'four',
                name: 'safeSet',
                component:()=>import('../components/four.vue'),
            }
        ]
    },
    {
      path: "/manage",
      name:'manage',
      component:()=>import('../views/manage.vue'),
        children: [
            {path: 'testM',name: 'testM',component:()=>import('../components/testManage.vue'),},
            {path: 'courseM',name: 'course',component:()=>import('../components/course.vue'),},
            {path: 'testC',name: 'testC',component:()=>import('../components/testC.vue'),},
            {path: 'courseC',name: 'courseC',component:()=>import('../components/courseC.vue')}
        ]
    },
{
    path: '/test',
    name: 'test',
    component:()=>import('../views/test.vue'),

},
    {
      path: "/testContent",
      name: "testContent",
      component:()=>import('../views/testContent.vue'),
    },
    {
      path: "/evaluation"  ,
        name: "evaluation",
        component:()=>import('../views/evaluation.vue'),
    },
    {
        // path后面“/:‘name’”为key
        path: "/login",
        name:"login",
        // 异步加载
        component:()=>import('../views/login.vue'),
        // // redirect:"/login/00",
        // // 重定向默认显示页面
        // children:[{
        //
        // //  子导航to时要写全路径（包括父路径）
        //     //     二级导航中的path没有“/”
        // }]
    },
    {
        path: "/study",
        name: "study",
        component: ()=>import('../views/study.vue'),
        redirect:"/study/zaixian",
        children:[
            {
                path: "studyPath",
                name: "studyPath",
                component:()=>import('../views/studyPath.vue'),
            }]
    },


]

//配置信息中需要页面的相关配置
const router=createRouter({
    history:createWebHashHistory(),
    routes
})
export default router;