<script setup>


import {onBeforeMount, ref} from "vue";
import axios from "axios";
let tableData=ref([])

const deleteT=async(index)=>{
  let Id=tableData[index].id

  let token = localStorage.getItem('token')
  let res= await axios.delete('/api/question/deleteById',{
    headers:{
      authorization:"Bearer "+token
    },
    params:{
      id:Id,
    }
  })
  console.log(res)
  if (res.data.code===200){
    console.log("删除成功")
    console.log(tableData)
    localStorage.setItem('item',JSON.stringify(tableData))
    window.location.reload()

  }else {
    console.log('失败')
  }

}
onBeforeMount(()=>{
  const test= localStorage.getItem('item')
  tableData=JSON.parse(test)
})
</script>

<template>
  <div>
    <el-table :data="tableData" style="width: 100%;padding:1vh">
      <el-table-column prop="text" label="题目" width="900">

      </el-table-column>
      <el-table-column label="删除" width="100">
        <template #default="scope">
          <el-button @click="deleteT(scope.$index)"
              size="small"
              type="danger">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped>

</style>