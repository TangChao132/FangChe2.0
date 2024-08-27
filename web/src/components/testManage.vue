<script setup>

import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import router from "@/router/index.js";
import {genFileId} from "element-plus";

const handleEdit = async (item) => {
  const subjectKey =item
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
    localStorage.setItem("item",JSON.stringify(res.data.data));
    await router.push('testC')
  }
}
const handleDelete = async (item) => {

  const token = localStorage.getItem("token");
  let res=await axios.delete('/api/question/deleteByType',{
    headers:{
      Authorization: 'Bearer '+token
    },
    params:{
      type:item
    }
  })
  if(res.data.code===200){
    window.location.reload()
  }else {
    console.log('删除失败')
  }
}

const tableData = ref([])

const acquireQuestion= async ()=>{
  const token = localStorage.getItem("token")
  let res=await axios.get('/api/question/searchTypes',{
    headers:{
      authorization: 'Bearer '+token
    }
  })
  if(res.data.code===200){
    tableData.value=res.data.data
    console.log(tableData)
  }else {
    console.log("获取失败")
  }
}


const upload = ref() // 注意这里不再需要指定 UploadInstance 类型

const handleExceed = (files) => {
  upload.value.clearFiles()
  const file = files[0]
  file.uid = genFileId()
  upload.value.handleStart(file)
}

const submitUpload = async () => {
  upload.value.submit()
  console.log(upload.value)
}

onMounted(()=>
    acquireQuestion()
)
</script>

<template>
  <div style="padding-top: 2vh;padding-left: 1vw;display: flex;flex-direction: column;justify-content: center;align-items: center;">
    <div class="table" style="height: 10vh">
      套题名称
      <el-upload
          ref="upload"
          class="upload-demo"
          action="http://26.107.171.13:3008/api/question/add"
          :limit="2"
          :on-exceed="handleExceed"
          :auto-upload="false">
        <template #trigger>
          <el-button type="primary">选择文件</el-button>
        </template>
        <el-button class="ml-3" type="success" @click="submitUpload">
          上传
        </el-button>
      </el-upload>
    </div>
    <div class="table" style="font-size: 2.5vh" v-for="(item,index) in tableData" :key="index" @click="item">
      {{item}}
      <button style="position: absolute;right: 8vw;top: 1vh" @click="handleEdit(item)">编辑</button>
      <button style="position: absolute;right: 2vw;top:1vh" @click="handleDelete(item)">删除</button>
    </div>
    <div style="border-top: 0.1vw solid black;width: 90%;"></div>
  </div>
</template>

<style scoped>
.table{
  position: relative;
  width: 90%;
  height: 5vh;
  font-size: 3vh;
  border-top: 0.1vw solid black;
  border-left: 0.1vw solid black;
  border-right: 0.1vw solid black;
  padding-left: 2vw;
  display: flex;
  align-items: center;
  .upload-demo{
    position: absolute;
    right: 2vw;
    display: flex;
    justify-content:space-between;
    align-items: center;
  }
}
</style>