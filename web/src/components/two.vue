<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";
import router from "@/router/index.js";

const tableData=ref([])
let token=localStorage.getItem('token')
let id=localStorage.getItem('id')
const num = parseInt(id, 10);
const clear=async ()=>{

  let res=await axios.delete('/api/history/delete',{
    headers:{
      Authorization:'bearer '+token
    },
    params:{
      uid:num
    }
  })
  console.log('删除',res)
  if (res.data.code===200){
    console.log('delete')
  }
  window.location.reload()
}
const acquire=async ()=>{
  let res=await axios.get('/api/history/search',{
    headers:{
      Authorization:'bearer '+token
    },
    params:{
      uid:num
    }

  })
  console.log('hhhhhhhhh',res.data)
  const responseData=res.data.data
  // let news=[]
  for(let item in responseData){
    let data={}
    console.log('item',item)
    data["type"] = item
    data["submit_time"] = responseData[item]
    console.log('data',data)
    tableData.value.push(data)
  }
  if (res.data.code===200){
    console.log('add')
  }
}


const jump=async ()=>{
  await router.push('/testContent')
  console.log('成功')
}

onMounted(()=>{
  acquire()
})
</script>

<template>
  <div class="two">
  <div >
    我做过的题
    <button class="deleteB" @click="clear">清空</button>
  </div>

    <div>
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="type" label="Type" width="180" />
        <el-table-column prop="submit_time" label="submit_time" />
        <el-table-column>
        <button class="reW" @click="jump">重做</button>
        </el-table-column>
      </el-table>
    </div>
  </div>

</template>

<style scoped>
.two{
  padding-top: 3vh;
  padding-left: 2vh;
  padding-right: 2vh;
  font-size: 2vh;
  .deleteB{
    position: relative;
    left: 44vw;
    bottom: 2vh;
    width: 6vw;
    height: 6vh;
    font-size: 2vh;
    border: none;
    background-color: #f5f5f5;
    color: #a50d0c;
  }
  .deleteB:hover{
    cursor: pointer;
  }
  .deleteB:active{
    color: #0056b3;
  }
}
.reW{
  border: none;
  background-color: white;
  font-size: 2vh;
}
.reW:hover{
  cursor: pointer;
  color: red;
}
.reW:active{
  color: #0056b3;
}
</style>
