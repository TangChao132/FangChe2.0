<template>
  <el-container style="padding: 0">
    <el-header class="header">
      <el-image
        class="title-img"
        fit="cover"
        :src="`${axios.defaults.baseURL}${res?.course.image}`">
      </el-image>
      <div class="courseImgCover"></div>
      <div class="title">
        <el-button color="#ffffff4c" class="back-btn" @click="back">
          返回
        </el-button>
        <el-text class="title-category">
          {{res?.course.category}}
        </el-text>
        <div class="title-name-div">
          <el-text class="title-name">
            {{res?.course.name}}
          </el-text>
          <el-button
            class="title-collect-btn"
            color="#ffffff4c"
            @click="collect"
            round
          >
            <el-icon style="margin-right: 5px; font-size: 22px">
              <Star/>
            </el-icon>
            收藏
          </el-button>
        </div>
        <el-text class="title-info">
          长期有效 · {{res?.course.visits}}人观看 · {{res?.collected}}人收藏
        </el-text>
      </div>
    </el-header>
    <el-main class="main">
      <el-tabs v-model="activeName" class="main-tabs">
        <el-tab-pane label="课程介绍" name="one">
          {{res?.course.introduction}}
        </el-tab-pane>
        <el-tab-pane label="课程目录" name="two">
          <el-collapse v-model="activeNames">
            <el-collapse-item
                class="chapters-item"
                v-for="item in chapters"
                :title="item.name"
            >
              {{ item.content }}
            </el-collapse-item>
          </el-collapse>
        </el-tab-pane>
        <el-tab-pane label="课程资料" name="three">
          <el-container>
            <el-main>
              <div v-if="videoList.length > 0">
                <video style="width: 100%" ref="myVideo" preload="metadata" :key="video? video.id : ''" controls>
                  <source :src="`${axios.defaults.baseURL}${video?.url}`">
                </video>
                <el-text
                    class="res-data"
                >
                  <el-icon>
                    <View/>
                  </el-icon>
                  {{video?.hits}}
                </el-text>
              </div>
              <div v-else>
                <el-empty style="width: 100%"/>
              </div>
            </el-main>
            <el-aside>
              <el-collapse v-model="activeNames" class="res-aside-collapse">
                <el-collapse-item
                    title="附件列表"
                    name="one"
                >
                  <div
                      v-for="item in videoList"
                  >
                    <el-text
                        class="res-aside-item-text"
                        truncated
                        @click="fileClick(item)"
                    >
                      {{item.name}}
                    </el-text>

                    <el-text
                        class="res-aside-item-time"
                        truncated
                    >
                      {{formatMinutesSeconds(item.timeLength)}}
                    </el-text>
                  </div>
                </el-collapse-item>
              </el-collapse>
            </el-aside>
          </el-container>

        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import axios from "axios";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";
import {Star, View} from "@element-plus/icons-vue";

const activeName = ref('one')
const activeNames = ref('one')
const route = useRoute();
const res = ref(null)
const chapters = ref([])
const videoList = ref([])
const cid = route.query.cid

let videoIdList = []

const video = ref(null)


onMounted(async () => {
  await axios.get('/api/course/search', {
    params: {
      id: cid
    }
  })
  .then(result => {
    console.log("success", result.data.data.rows[0])
    res.value = result.data.data.rows[0]
    chapters.value = JSON.parse(res.value.course.chapters)
    videoIdList = JSON.parse(res.value.course.resource)
  })
  .catch(async error => {
    console.error(error)
    ElMessage.error("课程加载失败")
    await router.push("/")
  })
  for(const item of videoIdList){
    await axios.get('/api/video/search', {
      params: {
        id: item
      }
    })
    .then(result => {
      console.log("video", result.data.data)
      videoList.value.push(result.data.data)
    })
    .catch(async error => {
      console.error(error)
      ElMessage.error("视频加载失败")
    })
  }
  video.value = videoList.value[0]
  await addClick(videoList.value[0].id)
})

const collect = async () => {
  const token = "bearer " + localStorage.getItem("token")
  await axios.post(`/api/course/collect/${res.value.course.id}`,{},{
    headers:{
      'Authorization': token
    }
  })
  .then(result => {
    const code = result.data.code
    if(code === 200){
      ElMessage.success("收藏成功")

    }
    else if(code === 400){
      ElMessage.warning("你已经收藏过该课程了")
    }
    else{
      ElMessage.error("请先登录")
    }
  })
  .catch(async error => {
    console.error("collect", error)
    ElMessage.error("网络请求失败")
  })
}

const fileClick = async (item) => {
  video.value = item
  await addClick(item.id)
}

function formatMinutesSeconds(totalSeconds) {
  totalSeconds = Math.floor(totalSeconds / 1000)
  // 计算分钟数
  let minutes = Math.floor(totalSeconds / 60);

  // 计算剩余的秒数
  let seconds = totalSeconds % 60;

  // 确保分钟和秒为两位数，不足时前面补0
  minutes = minutes < 10 ? '0' + minutes : minutes;
  seconds = seconds < 10 ? '0' + seconds : seconds;

  // 返回分秒格式的字符串
  return `${minutes}:${seconds}`;
}

const addClick = async (id) => {
  const form = new FormData()
  form.append("id", id)
  await axios.post('/api/video/list/click', form)
  .then(() => {})
  .catch(async error => {
    console.error("addClick", error)
    ElMessage.error("网络请求失败")
  })
}

const back = async () => {
  await router.push("/")
}
</script>

<style scoped>
.header{
  display: flex;
  position: absolute;
  width: 100%;
  height: 240px;
  padding: 0;
}
.main{
  display: flex;
  position: absolute;
  margin-top: 240px;
  width: 100%;
  padding: 50px;
}
.title-img{
  width: 100%;
  height: 100%;
  filter: blur(8px); /* 应用模糊效果 */
}
.courseImgCover {
  display: flex;
  z-index: 1;
  background: -webkit-linear-gradient(left, rgba(2, 2, 2, .9), rgba(2, 2, 2, .4), rgba(2, 2, 2, .3));
  background: -moz-linear-gradient(left, rgba(2, 2, 2, .9), rgba(2, 2, 2, .4), rgba(2, 2, 2, .3));
  background: -o-linear-gradient(left, rgba(2, 2, 2, .9), rgba(2, 2, 2, .4), rgba(2, 2, 2, .3));
  left: 0;
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
}
.title {
  display: flex;
  flex-direction: column;
  position: absolute; /* 绝对定位以覆盖在遮罩层上 */
  top: 20%; /* 根据需要调整位置 */
  left: 10%; /* 居中开始 */
  z-index: 2; /* 确保在遮罩层之上 */
  text-align: left;
}
.title-category{
  display: inline-block;
  width: 100%;
}
.title-name-div{
  display: flex;
  align-items: center;
}
.title-name{
  display: inline-block;
  width: 100%;
  color: white;
  font-size: 28px;
}
.title-collect-btn{
  position: absolute;
  left: 60vw;
  width: 280px;
  height: 50px;
  color: white;
  font-size: 20px;
}
.back-btn{
  position: absolute;
  top: -30px;
  left: 82vw;
  width: 80px;
  color: white;
  font-size: 15px;
}
.title-info{
  display: inline-block;
  width: 100%;
  color: white;
  font-size: 20px;
  margin-top: 60px
}
.main-tabs{
  width: 100%;
  --el-font-size-base: 20px;
}
.main-tabs :deep(.el-tabs__item){
  padding: 0 50px;
}
.chapters-item{
  --el-collapse-header-font-size: 20px;
  --el-collapse-content-font-size: 16px;
}
.chapters-item :deep(.el-collapse-item__header){
  padding: 10px;
}
.chapters-item :deep(.el-collapse-item__content){
  padding-left: 10px;
}
.res-aside-item-text{
  width: 70%;
  padding: 0 0 3px 10px;
  transition: color 0.3s ease;
  cursor: pointer;
}

.res-aside-item-time{
  padding: 0 0 3px 25px;
  cursor: default;
}

.res-aside-item-text:hover{
  color: #409eff;
}
.res-aside-collapse{
  --el-collapse-header-font-size: 20px;
}
.res-aside-collapse :deep(.el-collapse-item__header){
  padding: 10px;
}
</style>