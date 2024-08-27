<script setup>
import {onBeforeMount, reactive, ref} from "vue";
import router from "@/router/index.js";

import axios from "axios";
import HeaderMenu from "@/components/HeaderMenu.vue";
import {Edit, Plus} from "@element-plus/icons-vue";

const errorHandler = () => true
const inpath='/api/user/set-avatar'
let user=reactive({
  id:'',
  username:'',
  imgPath:'',
  account:'',
  identity:'',
  gender:'',

})


const ziyems=[
  {name:'我的课程',href:"one"},
  {name:'我的测评',href: "two"},
  {name: '基本设置',href: "three"},
  {name:'安全设置',href: "four"},
]

const toChange=(ziyem)=>{
  router.push(ziyem.href)
}
const getUserMessage=async()=>{
  const token = localStorage.getItem("token")
  let res = await axios.get('/api/user/info',{
    headers: {
      "Authorization": 'bearer '+token,
    }
  })
  if(res.data.code===200){
    console.log('res',res)
    user.username=res.data.data.nickname;
    user.account=res.data.data.account;
    user.imgPath=res.data.data.avatar;
    user.identity=res.data.data.role;
    user.gender=res.data.data.gender;
    user.id=res.data.data.id
  }
  console.log('user',user)
}

let token=localStorage.getItem('token')

const imageUrl=ref()
const handleAvatarSuccess = (response, uploadFile) => {
  imageUrl.value = URL.createObjectURL(uploadFile.raw)
  window.location.reload()
}


onBeforeMount(()=>{
  getUserMessage()
}
)
</script>

<template>
  <HeaderMenu></HeaderMenu>、
  <div class="all">
  <div class="demo-type">

      <el-upload
          style="z-index: 30"
          :headers='{Authorization:"bearer "+token }'
          class="avatar-uploader"
          :action=axios.defaults.baseURL+inpath
          :show-file-list="false"
          :on-success="handleAvatarSuccess">
        <img v-if="imageUrl" class="avatar" />

        <el-avatar :size="100" :src="axios.defaults.baseURL+user.imgPath" @error="errorHandler">
        </el-avatar>

      </el-upload>


    <div style=" max-width: 10vw;max-height: 4vh;margin-top: 1vh;font-size: 3vh">
      {{user.username===null?user.account:user.username}}
    </div>
  </div>


  <div class="bottom">
      <el-container >
        <div class="aside">
          <div v-for="(ziyem,index) in ziyems" :key="index" @click="toChange(ziyem)">
          <button style="width: 12vw;height: 6vh;font-size: 3vh;margin-bottom: 2vh;border:0.1vh solid whitesmoke ">
            {{ziyem.name}}
          </button>
          </div>
        </div>
        <el-main class="right">
          <router-view></router-view>
        </el-main>
      </el-container>

  </div>
  </div>
</template>

<style scoped>
.all{
  width: 100vw;
  padding-top: 6.5vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.demo-type{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 76vw;
  height: 25vh;
  background-color: #c9cfd5;
}
.bottom{
  width: 100vw;
  height: 50vh;
  padding-top:2vh ;
  padding-left: 12vw;
  padding-right: 13vw;
}
.aside{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 0.1vw solid #c4bdbd;
  width: 18vw;
  height: 50vh;
}
button:hover{
  cursor: pointer;
  background-color: #e0e0e0;

}
.right{
  width: 50vw;
  border: 0.1vh solid #c4bdbd;
  max-height: 500vh;
  padding: 0;
  margin-left: 1vw;
}
.avatar-uploader{
  border: none;
}
.el-container{
  width: 76vw;
}
.icon{
  font-size: 4vh;
  position: absolute;
  top: 17.5vh;
  z-index: 0;
  color: red;
}
.icon:hover{
  z-index: 300;
}
</style>