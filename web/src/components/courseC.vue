<script setup>
import {ref, onMounted, onBeforeMount} from 'vue';
import axios from "axios";
import router from "@/router/index.js";

const search = ref();
const dialogVisible = ref(false)
const tableData=ref([])
let video=ref([])
const Tid=ref()
let token=localStorage.getItem('token');
let newName=ref()
// 查询
const searchC=async ()=>{
  console.log(search.value)

  if(!isNaN(search.value) && search.value !== ''){
    const numericValue = Number(search.value);
    let res=await axios.get('/api/video/list',{
      headers:{
        Authorization:"Bearer "+token
      },params:{
        timeLength:numericValue
      }
    })
    if(res.data.code===200){
      console.log('搜索成功')
      video.value=res.data.data
    }
  }


  else if(typeof search.value==="string"){
  let res=await axios.get('/api/video/list',{
    headers:{
      Authorization:"Bearer "+token
    },params:{
      name:search.value
    }
  })
  console.log(res)
  if(res.data.code===200){
    console.log('搜索成功')
    video.value=res.data.data
  }
}
}
// 删除
const handleDelete = async (index) => {

  console.log('222222222222',tableData._rawValue)
  console.log('Tid',Number(Tid.value))
  let res=await axios.delete('/api/video/delete',{
    headers:{
      Authorization:"Bearer "+token
    },
    params:{
      id:tableData._rawValue[index]
    }
  })
  console.log(res)
  if(res.data.code===200){
    console.log('成功')
    tableData._rawValue=tableData._rawValue.splice(index,1)
    console.log(tableData._rawValue)
    // update()

  }

};
// 更新

const update=async ()=>{
  let res=await axios.put('/api/course/update',{id:Tid.value,
    resources:JSON.stringify( tableData._rawValue)
  },{
    headers:{
      Authorization:"Bearer "+token
    }
  })
  console.log('333333333333333333',res)
  if(res.data.code===200){
    console.log('更新成功')
  }
}


// 修改名称
const change=async (index)=>{
  dialogVisible.value=!dialogVisible.value;
  let data=new FormData
  data.append("id",video.value[index].id)
  data.append("name",newName.value)

  let res=await axios.put('/api/video/put',data,{
    headers:{
      Authorization:"Bearer "+token
    }
  })
  if(res.data.code===200){
    await router.push('courseM')

  }

}
// 从local中获取
const acquireVideo=async()=>{
  video.value=JSON.parse(localStorage.getItem('video'))
  tableData.value=JSON.parse(localStorage.getItem('tableDate'))
  Tid.value=localStorage.getItem('Tid')

}



onBeforeMount(()=>{
  acquireVideo()

})


</script>

<template>
  <div class="videoAll">


<h3>
  课程视频管理
</h3>
  <div style="padding-top: 1vh">
    <el-table :data="video" style="width: 100%">
      <el-table-column label="名称" prop="name" />
      <el-table-column label="时长" prop="timeLength" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="small" placeholder="请输入查询内容" />
          <button @click="searchC">搜索</button>
        </template>
        <template #default="scope">
          <el-button
              size="small"
              @click="dialogVisible = true">修改名称</el-button>

          <el-dialog v-model="dialogVisible" title="修改视频名称" width="30%" draggable>
            <template #footer>
      <div><input v-model="newName" placeholder="请输入新名称"></div>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="change(scope.$index)"
        >确认</el-button>

            </template>
          </el-dialog>

          <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  </div>
</template>

<style scoped>


.videoAll{
  padding-top: 3vh;
  padding-left: 2vh;
  padding-right: 2vh;
}
</style>