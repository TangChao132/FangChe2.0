<script setup>
import { ref, onMounted } from 'vue';
import {ElMessage} from "element-plus";
import axios from "axios";
import router from "@/router/index.js";

const id = ref("1808686073253060609"); // 初始化ID值


//收藏函数
const isFavorite = ref(false);
const toggleFavorite = async () => {

  id.value = courseData.value.id;
  try {
    if (isFavorite.value) {
      // 如果已经收藏，则取消收藏
      let result1 = await axios.post(`/api/course/collect/remove/${id.value}`);

      if(result1.data.code === 200){
        console.log(result1.data.code)
        ElMessage.success('课程已取消收藏')

      }

    } else {
      //console.log(id.value)
      // 如果未收藏，则添加收藏
      let result2 = await axios.post(`/api/course/collect/${id.value}`);

      if(result2.data.code === 200)
      {
        console.log(result2.data.code)
        ElMessage.success('课程已收藏')
        isFavorite.value = !isFavorite.value; // 切换收藏状态
      }
      else {
        ElMessage.error('请先登录后收藏 ')
      }
    }

  } catch (error) {
    console.error('操作失败，错误信息:', error);
    ElMessage.error(isFavorite.value ? '取消收藏失败' : '收藏失败');
  }
};


//菜单
const menuItems = ref([
  { id: 'menu1', text: '课程介绍', type: 'info' },
  { id: 'menu2', text: '课程目录', type: 'info' },
  // 可以添加更多菜单项
]);

const currentMenu = ref('menu1'); // 初始菜单项

const selectMenu = (item) => {
  currentMenu.value = item.id;
  item.isSelected = true;
};


const teacherData = ref({

  name:'',

})



const searchTeacher = async () => {
  try {
    console.log(`发起请求获取ID为 ${id.value} 的老师信息...`);
    const response = await axios.get(`/api/user/info/${id.value} `,);
    console.log('返回的数据:', response.data.data.nickname);
     teacherData.value.name = response.data.data.nickname;
    //console.log('teacherData.name:',teacherData.value.name);
  }catch (error) {
    console.error('请求失败，错误信息:', error);
    //ElMessage.error("获取课程信息失败");
  }
}



const courseData = ref({
  id:'',
  name:'',
  introduction:'',
  chapters:[],
  resource:'',
  teacher_Id:'',
  category:'',
})

// 获取课程数据的函数
const getCourseData = async () => {
  try {
    console.log(`发起请求获取ID为 ${id.value} 的课程信息...`); // 打印请求信息
    // 发起GET请求，获取id为当前id的课程信息
    const response = await axios.get(`/api/course/search`);
    console.log('请求成功，返回的数据:', response.data); // 打印返回的数据

    // 检查返回数据中的课程列表
    let courseFound = false;

    for (let i = 0; i < response.data.data.rows.length; i++) {
      const courses = response.data.data.rows[i].course;
      if (courses.id === id.value) {
        // 将找到的课程信息赋值给courseData
        courseData.value = {
          id: courses.id,
          name: courses.name,
          introduction: courses.introduction,
          chapters: courses.chapters,
          resource: courses.resource,
          teacher_Id: courses.teacherId,
          category: courses.category || '未分类' // 如果category为null，则设置为'未分类'

        };
        courseFound = true;
        break; // 找到匹配的课程后，退出循环
      }
    }

    if (!courseFound) {
      console.error(`没有找到ID为 ${id.value} 的课程信息`);
      ElMessage.error("没有找到对应的课程信息");
    }

    // console.log('courseData.name');

  } catch (error) {
    console.error('请求失败，错误信息:', error); // 打印错误信息
    ElMessage.error("获取课程信息失败");
  }
};

onMounted(async () => {
  // 当组件挂载后，调用获取课程信息的函数
  await getCourseData();
  id.value = courseData.value.teacher_Id;
  await searchTeacher();

  console.log(courseData.value.chapters);

});
// 返回主页函数
const chang_page = async () => {
  await router.push("/")
};






</script>


<template>

  <div class="container">

    <div class="navbar">
      <ul>
        <li v-for="item in menuItems" :key="item.id" >

          <el-link :underline="false" :type="item.type" @click="selectMenu(item)">{{ item.text }}</el-link>
        </li>
      </ul>


    </div>

    <!-- 内容区域 -->
    <div class="content">
      <div v-if="currentMenu === 'menu1'">
        {{ courseData.introduction }}
      </div>
      <div v-else-if="currentMenu === 'menu2'">

          <div v-for="(chapter, index) in courseData.chapters" :key="index">
            <div>{{ chapter.name }}：{{ chapter.content }}</div>
          </div>

      </div>
    </div>

    <!-- 返回按键 -->
    <div class="return">
      <el-link type="info" :underline="false" @click="chang_page()">
        ← 返回
      </el-link>
    </div>

    <!-- 收藏按键 -->
    <div class="toggleFavorite">
      <el-button @click="toggleFavorite">{{ isFavorite ? '取消收藏' : '收藏课程' }}</el-button>
    </div>

    <!-- 显示课程名称 -->
    <div class="course-name">
      {{ courseData.name }}
    </div>

    <!-- 显示教师 -->
    <div class="teacherId">
      教师名称: {{teacherData.name}}
    </div>



    <!-- 3个白色的方块 -->
    <div class="block1"></div>
    <div class="block2"></div>
<!--    <div class="block3"></div>-->

    <!--图片-->
    <div class="jpg">
      <img src="@/assets/login_bg.jpg " />
    </div>

  </div>
</template>




<style scoped>

.course-name {
  position: fixed; /* 固定位置 */
  top: 100px; /* 距离顶部20像素 */
  left: 200px; /* 距离左侧20像素 */
  /*background-color: #f0f2f5; !* 背景颜色 *!*/
  /*padding: 10px; !* 内边距 *!*/
  /*border: 1px solid #ccc; !* 边框 *!*/
  /*border-radius: 5px; !* 圆角 *!*/
  /*box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); !* 阴影 *!*/
  font-size: 40px; /* 字体大小 */
  color: #333; /* 字体颜色 */
  z-index: 1;
}

.teacherId {
  position: fixed; /* 固定位置 */
  top: 200px; /* 距离顶部20像素 */
  left: 200px; /* 距离左侧20像素 */
  font-size: 20px; /* 字体大小 */
  color: #333; /* 字体颜色 */
  z-index: 1;
}

.jpg{
  width: 100px; /* 设置图片宽度为200像素 */
  height: 50px; /* 设置图片高度为150像素 */
  position: absolute; /* 绝对定位 */
  top: 80px; /* 距离顶部50像素 */
  left: 20px; /* 距离左侧100像素 */
  z-index: 0;
}


.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f0f2f5;
  z-index: 2;
}

.navbar {
  z-index: 2;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px 30px;
  background-color: transparent; /* 或者根据需要设置背景颜色 */
  position: fixed; /* 固定位置 */
  top: 350px; /* 距离顶部20像素 */
  left: 200px; /* 距离左侧20像素 */
}

.navbar ul {
  list-style: none; /* 去除列表前面的黑色点 */
  display: flex; /* 设置为flex布局 */
  justify-content: space-between; /* 在父元素内均匀分布子元素 */
}


.navbar li {
  font-size: 10px; /* 菜单项的字体大小 */
  font-family: 'Arial', sans-serif; /* 菜单项的字体 */
  margin-right: 30px; /* 添加一些间隔 */
}

.navbar a {
  text-decoration: none; /* 去除下划线 */
}

.return {
  z-index: 1;
  position: fixed; /* 固定位置 */
  top: 110px; /* 距离顶部20像素 */
  left: 50px; /* 距离左侧20像素 */
  background-color: white; /* 背景颜色 */
  padding: 10px; /* 内边距 */
  border: 1px solid #ccc; /* 边框 */
  border-radius: 5px; /* 圆角 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* 阴影 */
  font-size: 10px; /* 字体大小 */
  color: deepskyblue; /* 字体颜色 */
}


.toggleFavorite {
  z-index: 3;
  position: fixed; /* 固定位置 */
  top: 350px; /* 距离顶部20像素 */
  left: 850px; /* 距离左侧20像素 */
  /*padding: 10px; !* 内边距 *!*/
  /*border: 1px solid #ccc; !* 边框 *!*/
  /*border-radius: 50px; !* 圆角 *!*/
  /*box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); !* 阴影 *!*/
  font-size: 10px; /* 字体大小 */
  color: deepskyblue; /* 字体颜色 */
}


.content {
  z-index: 2;
  margin-top: 20px;

  display: block;
  justify-content: center;
  align-items: center;
  padding: 10px 0;
  background-color: #f0f2f5; /* 或者根据需要设置背景颜色 */
  position: fixed; /* 固定位置 */
  top: 450px; /* 距离顶部20像素 */
  left: 250px; /* 距离左侧20像素 */
}
/*下面方块*/
.block1 {
  position: absolute; /* 绝对定位 */
  width: 800px; /* 方块宽度 */
  height: 200px; /* 方块高度 */
  top: 450px; /* 垂直居中 */
  left: 200px; /* 水平居中 */
  z-index: 1; /* 设置z-index为-1，确保方块位于所有其他元素之下 */
  background-color: #f0f2f5; /* 背景颜色 */
  padding: 10px; /* 内边距 */
  border: 1px solid #ccc; /* 边框 */
  border-radius: 5px; /* 圆角 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* 阴影 */
}
/*顶层方块*/
/*.block3 {*/

/*  position: absolute; !* 绝对定位 *!*/
/*  width:  1270px; !* 方块宽度 *!*/
/*  height: 300px; !* 方块高度 *!*/
/*  top: 5px; !* 垂直居中 *!*/
/*  left: 5px; !* 水平居中 *!*/
/*  z-index: 2; !* 设置z-index为-1，确保方块位于所有其他元素之下 *!*/
/*  background-color: white; !* 背景颜色 *!*/
/*  padding: 10px; !* 内边距 *!*/
/*  border: 1px solid #ccc; !* 边框 *!*/
/*  border-radius: 5px; !* 圆角 *!*/
/*  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); !* 阴影 *!*/
/*}*/
/*菜单方块*/
.block2 {
  position: absolute; /* 绝对定位 */
  /*background-color: white; !* 白色背景 *!*/
  width:  800px; /* 方块宽度 */
  height: 80px; /* 方块高度 */
  top: 330px; /* 垂直居中 */
  left: 200px; /* 水平居中 */
  z-index: 1; /* 设置z-index为-1，确保方块位于所有其他元素之下 */
  background-color: #f0f2f5; /* 背景颜色 */
  padding: 10px; /* 内边距 */
  border: 1px solid #ccc; /* 边框 */
  border-radius: 5px; /* 圆角 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* 阴影 */
}

</style>

