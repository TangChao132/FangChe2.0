<script setup>
import { Timer } from '@element-plus/icons-vue'
import {onMounted, ref} from "vue";
import axios from "axios";

const handleEdit = (index, row) => {
  console.log(index, row)
}
const handleDelete = (index, row) => {
  console.log(index, row)
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
    console.log(tableData.value)
    console.log("获取成功")
  }else {
    console.log("获取失败")
  }
}





onMounted(()=>
    acquireQuestion()
)
</script>

<template>
  <div>
    <h1>删除</h1>

    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="名称" width="180">
          <template #default="scope">
            <el-popover effect="light" trigger="hover" placement="top" width="auto">
              <template #default>
                <div>name: {{ scope }}</div>
              </template>
              <template #reference>
                <el-tag>{{ scope.row.name }}</el-tag>
              </template>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="进行操作">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
              进入
            </el-button>
            <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>


</template>

<style scoped>

</style>