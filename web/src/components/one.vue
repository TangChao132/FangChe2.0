<script setup>


import {onBeforeMount, onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

let tableData=ref([])
const acquire=async ()=>{
 let id=localStorage.getItem('id')
  let token=localStorage.getItem('token')
  let res=await axios.get('/api/course/collect/get/'+id,{
    headers:{
      Authorization:'bearer '+token
    }
  })
  if(res.data.code===200){
    tableData.value=res.data.data
    console.log('888888888888888',res.data.data[0].teacherId)
    localStorage.setItem('Tid',res.data.data[0].teacherId)
  }
}
const acquireTeacherId=async ()=>{
  console.log('33333333333333333333',tableData)
  let Tid=localStorage.getItem('Tid')
  let token=localStorage.getItem('token')
  let res=await axios.get('/api/user/info/'+Tid,{
    headers:{
      Authorization:'bearer '+token
    }
  })
  console.log('2222222222222222222',res.data)
}




const handleDelete=async (index)=>{
  const token = localStorage.getItem("token")
  let res = await axios.delete('/api/course/collect/remove/'+tableData._rawValue[index].id,{
    headers: {
      "Authorization": 'bearer '+token,
    }
  })
  if(res.data.code===200){
    ElMessage({
      message: '取消收藏成功',
      type: 'success',
      plain: true,
    })
  }
}


// 根据教师id获取教师名
onMounted(()=>{

  acquireTeacherId()
})
onBeforeMount(()=>{
  acquire()
})
</script>

<template>
  <div class="one">
    <div class="first">
      我的收藏
    </div>
    <div class="table">
      <el-table class="tableC" :data="tableData" stripe style="width: 100%">
        <el-table-column style="color: #181818" prop="name" label="Name" width="180" />
        <el-table-column prop="teacher" label="teacher" >黄{{}}</el-table-column>
<!--        <el-table-column prop="" label="取消收藏" />-->
        <el-table-column label="取消收藏">
          <template #default="scope">
          <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index)"
          >
            Delete
          </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<style scoped>
.one{
  font-size: 2vh;
  padding: 2vh;
  .table{
    margin-top: 1vh;
      .cell{
        color: #181818;
      }

  }
}

</style>