<script setup>
import {onBeforeMount, reactive, ref} from "vue";
import {Avatar} from "@element-plus/icons-vue";
import router from "@/router/index.js";
import axios from "axios";
import {ElMessage} from "element-plus";

const manager=ref(false)


let userInfo = reactive({
  nickname: "请登录",
  avatar: "",

})

// 获取用户信息
const getUserInfo = async () => {
  const token = localStorage.getItem("token");
  console.log(`token:${token}`)
  if (token) {
    const response = await axios.get("/api/user/info", {headers: {'Authorization': `Bearer ${token}`}});
    console.log(response);
    if (response.data.code === 200) {
      userInfo.nickname = response.data.data.nickname;
      userInfo.avatar = response.data.data.avatar;
    } else if (response.data.code === 303){
      ElMessage.error("登录已过期，请重新登录")
      // 清除token
      localStorage.setItem("token", "")
      setWitchMenu()
    }
  }
}

// 已经登陆的用户菜单栏选项
const menu1 = [
  {
    index: "/user/one",
    label: "用户中心",
  }
  ,
  {
    index: "/about",
    label: "关于",
  },
  {
    index: '/manage/courseM',
    label: "管理"
  },
  {
    index: "/logout",
    label: "退出登录",
  }
]
// 未登录的用户菜单栏选项
const menu2 = [
  {
    index: "/login",
    label: "登录",
  }
  ,
  {
    index: "/about",
    label: "关于",
  }
  ,
]
// 显示的用户菜单栏选项
const menu = reactive([]);
const setWitchMenu = () => {
  if (localStorage.getItem("token")) {
    menu.push(...menu1);
  } else {
    menu.push(...menu2);
  }
}
const userRoute = (index) => {
  if (index === "/logout") {
    ElMessage.success("退出登录成功")
    router.push("/login")
    localStorage.clear()

  }else if(index==='/manage/courseM'){
    let role=localStorage.getItem('role')
    if(role==='2'||role==='3'){
      router.push("/manage/courseM")
    }else {
      ElMessage({
        message: '无权限',
        type: 'warning',
        plain: true,
      })

    }

  }else {
    router.push(index)
  }


}





onBeforeMount(()=>{
  getUserInfo();
  setWitchMenu();

})
const toTest=async ()=>{
  await router.push('/test')
}
const toMain=async ()=>{
  await router.push('/')
}

</script>

<template>

      <el-header class="menu-header">
        <el-menu
            :default-active="activeIndex"
            mode="horizontal"
            @select="handleSelect"
            class="menu"
            :ellipsis="false"
        >

          <img
              class="main-logo"
              src="../assets/logo3.png"
              alt="Logo"
          />


          <el-menu-item @click="toMain" index="1" style="font-size: 28px; margin-left: 40px">在线课程</el-menu-item>
          <el-menu-item @click="toTest" index="2" style="font-size: 28px; margin-left: 40px">测评</el-menu-item>

<!--          <div class="menu-right"/>-->
<!--          <el-sub-menu index="3">-->
<!--            <template #title>-->
<!--              <el-icon>-->
<!--                <el-icon :size="60"><Avatar /></el-icon>-->
<!--              </el-icon>-->

<!--              <div style="font-size: 20px">-->
<!--                {{'用户'}}-->
<!--              </div>-->

<!--            </template>-->
<!--            <el-menu-item index="/" @click="toUser">用户中心</el-menu-item>-->
<!--            <el-menu-item @click="">关于</el-menu-item>-->
<!--            <el-menu-item v-if="manager" @click="toManage">管理</el-menu-item>-->
<!--            <el-menu-item @click="toBack">退出登录</el-menu-item>-->
<!--          </el-sub-menu>-->

          <div class="menu-right"/>
          <el-sub-menu index="3">
            <template #title>
              <el-icon>
                <el-icon :size="60">
                  <Avatar />
                </el-icon>
              </el-icon>
              <div style="font-size: 20px">
                {{userInfo.nickname}}
              </div>

            </template>
            <!--            循环遍历生成菜单-->

            <el-menu-item v-for="i in menu" :index="i.index" @click="userRoute(i.index)">
              {{ i.label }}
            </el-menu-item>
          </el-sub-menu>

        </el-menu>

      </el-header>

</template>

<style scoped>
* {
  padding: 0;
  margin: 0;
}

.menu-header{
  position: fixed;
  width: 100%;
  background-color: white;
  z-index: 99;
}
.menu {
  height: 72px;
}
.main-logo {
  width: 150px;
  height: 100px;
  margin-top: -5px;
  margin-left: 5%;
}

.menu-right {
  flex-grow: 1;
}
</style>
