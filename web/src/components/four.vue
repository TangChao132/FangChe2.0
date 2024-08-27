<script setup>

import {reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
let password=localStorage.getItem('password')
let account=localStorage.getItem('account')

const form = reactive({
  oldpassword:'',
  newpassword:''
})
const change=async ()=>{
  dialogFormVisible.value=!dialogFormVisible
  console.log(form)
  let token=localStorage.getItem('token')

  let res=await axios.put('/api/user/password/update',{
        old_password:form.oldpassword,
        new_password:form.newpassword,
      }
  ,{
    headers:{
      Authorization:'bearer '+token
    }
  })

  if(res.data.code===200){
    console.log('修改成功')
    ElMessage({
      showClose: true,
      message: '修改成功',
      type: 'success',
    })
  }else {
    console.log('修改失败')
    ElMessage({
      showClose: true,
      message: '修改失败',
      type: 'error',
    })
  }


}
const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
</script>

<template>
  <div class="four">
  <div style="font-size: 3vh">
    安全设置
  </div>
  <form>
    <div style="margin-top: 1.2vh;font-size: 2.5vh">已绑定邮箱 {{account}} 不可更改</div>
    <div style="font-size: 2.5vh">
      密码：可修改
      <el-button text style="font-size: 2vh" @click="dialogFormVisible = true">修改</el-button>

      <el-dialog v-model="dialogFormVisible" title="密码修改">
        <el-form :model="form">
          <el-form-item label="请输入旧密码" :label-width="formLabelWidth">
            <el-input v-model="form.oldpassword" autocomplete="off" />
          </el-form-item>
          <el-form-item label="请输入新密码" :label-width="formLabelWidth">
            <el-input v-model="form.newpassword" autocomplete="off" />
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="change">
          确认
        </el-button>
      </span>
        </template>
      </el-dialog>

    </div>
  </form>
  </div>
</template>

<style scoped>
.four{
  padding: 3vh;
  color: black;
}
</style>
