<template>
  <div class="body">
    <el-container class="container">
      <Header></Header>
      <el-main class="main">
        <div class="ad">
          <div class="img-ad">
            <img src="../assets/main/u1072.png" alt="ad" style="min-width: 245px; min-height: 345px;">
          </div>

          <div class="text-ad">
            <el-text class="text-ad1">在线课程</el-text>
            <el-text class="text-ad2">多元化培训课程，OMO培训服务、直播教学服务</el-text>
          </div>

        </div>
        <div class="search">
          <el-input v-model="queryParams.keyword" class="search_input" placeholder="请输入关键字" size="large">
          </el-input>
          <el-button size="large" :icon="Search" type="primary" class="search_button" @click="searchCourse">
            搜索
          </el-button>
        </div>

        <div class="course">
          <div class="course-list">
            <el-row :gutter="20">
              <el-col :span="6" v-for="(course, index) in courseList" :key="index">
                <div class="course-card">
                  <el-card class="grid-content" @click="clickCard(course.course.id)">
                    <img :src="(course.course.image === '' || course.course.image === null ? 'https://naive-ui.oss-cn-beijing.aliyuncs.com/carousel-img/carousel1.jpeg' : axios.defaults.baseURL + course.course.image)"
                         class="course-image" alt="fangche">
                    <template :style="{ whiteSpace: 'nowrap' }" #footer>{{course.course.name}}
                      <br>
                      <el-icon><Star /></el-icon>
                      <span style="position: relative;bottom: 1.5px;right: -5px">{{course.collected}}</span>
                      <span style="margin-left: 180px"><el-icon><User /></el-icon>
                      <span style="position: relative;bottom: 1.5px;right: -5px">{{course.course.visits}}</span></span>

                    </template>
                  </el-card>
                </div>
              </el-col>
            </el-row>
          </div>

          <div class="pagination">
            <el-pagination background layout="prev, pager, next"
                           :total="total"
                           :page-size="queryParams.page_size"
                           @current-change="handlePageChange"/>
          </div>
        </div>
       <Footer></Footer>
      </el-main>

    </el-container>

  </div>
</template>

<script setup>
import Header from "../components/HeaderMenu.vue";
import {Search, Star, User} from "@element-plus/icons-vue";
import axios from "axios";
import { onMounted, reactive, ref } from "vue";
import { ElMessage } from "element-plus";
import Footer from "@/components/Footer.vue";
import router from "@/router/index.js";

let courseList = ref([]);
let total = ref(0);
let queryParams = reactive({
  page: 1,
  page_size: 12,
  keyword: "",
});

// 获取课程列表
const getCourseList = async () => {
  const response = await axios.get("/api/course/search", { params: queryParams });
  console.log('111111111111111111111111',response.data)
  if (response.data.code !== 200) {
  ElMessage.error("请求课程列表失败");
  return;
  }
  courseList.value = response.data.data.rows;
  total.value = response.data.data.total;
  console.log(courseList.value);
  console.log(total);
};

onMounted(() => {
  getCourseList();
});

const searchCourse = async () => {
  // 重置分页
  queryParams.page = 1;
  const response = await axios.get("/api/course/search", { params: queryParams });
  if (response.data.code !== 200) {
  ElMessage.error("请求课程列表失败");
  return;
  }
  courseList.value = response.data.data.rows;
  total.value = response.data.data.total;
  console.log(courseList.value);
  console.log(total);
};

const handlePageChange = (page) => {
  queryParams.page = page;
  getCourseList();
};

const clickCard = async (id) => {
  await router.push(`courseDetail?cid=${id}`)
}
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}
.body {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100vw;
  background-color: white;
}
.container {
  height: 100vh;
}

.main {
  max-height: 100vh; /* 减去头部的高度（假设为 50px） */
  overflow: auto;
}
.ad {
  width: 100%;
  height: 35%;
  margin-top: -25px;
  background-color: #409EFF;;
  background-image: url("../assets/main/u314.jpg");
  z-index: -1;
}
.img-ad {
  width: 20%;
  margin: 5% 72%;
  z-index: 1;
}
.text-ad {
  position: relative;
  color: white;
  font-size: 20px;
  font-weight: bold;
  margin: -20% 0 0 10%;
  z-index: 2;
}
.text-ad1 {
  color: black;
  font-size: 30px;
  font-weight: bold;
  display: block;
}
.text-ad2 {
  color: black;
  font-size: 18px;
  display: block;
}
.course {
  width: 80%;
  margin: 0 auto 50px;
  z-index: -1;
}
.course-image {
  width: 100%;
  height: 200px;
}
.course-list {
  width: 100%;
  justify-content: center;
  align-items: center;
}

el-row {
  margin-bottom: 20px;
}
el-row:last-child {
  margin-bottom: 0;
}
el-col {
  border-radius: 4px;
}
.course-card {
  transition: all 0.3s ease;
}
.course-image[data-v-37ec0ed8]{
  height: 154px;
}
.course-card:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  margin-top: 40px;
  cursor: pointer;
}
.pagination {
  display: flex;
  justify-content: center;
  margin: 50px auto;
}
.search {
  display: flex;
  align-items: center;
  margin: 40px auto 10px;
  width: 400px;
  height: 50px;
  z-index: 1;
}
.search_button {
  width:100px;
}


</style>
