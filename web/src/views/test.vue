<script setup>
import HeaderMenu from "@/components/HeaderMenu.vue";

import {onMounted, ref} from "vue";
import axios from "axios";
import router from "@/router/index.js";

//前端存放测试题
const subjects=ref([])

// 点击不同的测试题

const select=async (subject)=>{

  const subjectKey =subject
  let token = localStorage.getItem('token')
  let res=await axios.get('/api/question/searchQuestion',{
    headers:{
      authorization:"Bearer "+token
    },
    params:{
      type:subjectKey,
    }
  })
  if(res.data.code===200){
    localStorage.setItem("subject",JSON.stringify(res.data.data));
    console.log(res.data.data);
    await router.push('/testContent')
    // 全剧刷新有时可以不用
  }

}
// 获取所有的测试题
const acquireQuestion= async ()=>{
  const token = localStorage.getItem("token")
  let res=await axios.get('/api/question/searchTypes',{
    headers:{
      authorization: 'Bearer '+token
    }
  })
  if(res.data.code===200){
    subjects.value=res.data.data
    // console.log("获取成功")
  }else {
    console.log("获取失败")

  }
}
onMounted(()=>
    acquireQuestion()
)
</script>

<template>

<HeaderMenu></HeaderMenu>
  <div class="all">

  <div class="bottom">
    <div v-for="(subject,index) in subjects" :key="index" @click="select(subject)" class="connect">
      {{subject}}
      测试题
<!--      <button @click="deleteSubject(subject,$event)" class="cButton" ><el-icon><CloseBold /></el-icon></button>-->
    </div>
  </div>
  </div>
</template>

<style scoped>

.all{
  display: flex;
  width: 100vw;

  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.bottom{
  margin-top: 8.5vh;
  width: 73vw;
  max-height: 200vh;
  padding: 1.5vh;
  display: flex;
  border: 0.1vh solid whitesmoke;
  flex-wrap: wrap;
  .connect{
    width: 17vw;
    height: 15vh;
    margin-left: 0.6vw;
    margin-top: 2.5vh;
    background-color: #cfd9e4;
    transition: box-shadow 0.5s ease;
    border-radius: 2vh;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    .cButton{
      position: absolute;
      top: 1.5vh;
      right: 0.9vw;
      border: none;
      font-size: 3vh;
      background-color: white;
      color: red;
    }
  }
}
.cButton:hover{
  cursor: pointer;
  background-color: #181818;
}
.connect:hover{
  box-shadow:  0 0.4vh 0.8vh gray;
  cursor: pointer;
}
</style>