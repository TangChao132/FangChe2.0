<script setup>
import HeaderMenu from "@/components/HeaderMenu.vue";
import router from "@/router/index.js";


const toCourse=async ()=>{
  await router.push("courseM")
  window.location.reload()
}
const toTest=async ()=>{
  await router.push("testM")
  window.location.reload()
}
</script>

<template>
  <HeaderMenu />

  <div class="all">
  <div class="left">
    <button class="MB" @click="toCourse" style="font-size: 3vh">
      课程管理
    </button>
    <button class="MB" @click="toTest" style="margin-top: 15vh;font-size: 3vh">
      测评管理
    </button>
  </div>
    <div class="main">
    <router-view></router-view>
    </div>
  </div>


</template>

<style scoped>
  .all{
    padding-top: 13vh;
    padding-left: 5vw;
    padding-right: 5vw;
    display: flex;
    width: 100vw;
    .left{
      width: 18vw;
      height: 40vh;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      .MB{
        box-shadow: 1px 2px #0056b3;
      }
      .MB:hover{
        cursor: pointer;
      }
      .MB:active{
        color: #0056b3;
      }
    }
    .main{
      width: 100%;
      max-height: 100vh;
      min-height: 60vh;
      border: 0.1vh solid black;
    }
  }
</style>