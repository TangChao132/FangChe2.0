<script setup>
import {reactive, ref, onMounted, computed} from 'vue'
import axios from "axios";
import {ElMessage} from "element-plus";
import {Plus} from "@element-plus/icons-vue";
const account=localStorage.getItem('user.account')
const sizeForm = reactive({
  nickname:account,
  delivery: false,
  signature:'',
  gender:'',
  introduction:'',
})

let Sex=ref('保密')
const isState = ref(false);


const buttonText = computed(() => {
  return isState.value ? '保存' : '修改';
});
// 定义一个方法来切换按钮文本

const change=()=>{
  isState.value = !isState.value;

  if(!isState.value){
    submit()
    if(sizeForm.gender==='-1'){
      Sex.value="保密"

    }else if(sizeForm.gender==='0'){
      Sex.value='女'
    }else if(sizeForm.gender==='1'){
      Sex.value='男'
    }
  }
}

// 更新信息
const  submit= async() =>{

  const token = localStorage.getItem("token")
  let res=await axios.put('/api/user/update',
    {
      nickname:sizeForm.nickname,
      gender:sizeForm.gender,
      signature:sizeForm.signature,
      introduction:sizeForm.introduction,
  },{
      headers:{
        authorization:'Bearer '+token,
      }
  }

  )
  if(res.data.code===200){
    ElMessage({message:"修改成功",type:'success'})

  }else {
    ElMessage({message:"修改失败",type:'error'})
  }
}

const getUserMessage=async()=>{
  const token = localStorage.getItem("token")
  let res = await axios.get('/api/user/info',{
    headers: {
      "Authorization": 'bearer '+token,
    }
  })
  if(res.data.code===200){
    sizeForm.nickname=res.data.data.nickname;
    sizeForm.signature=res.data.data.signature;
    sizeForm.gender=res.data.data.gender;
    sizeForm.introduction=res.data.data.introduction;
  }
}





onMounted(()=>{
  getUserMessage()
})
</script>

<template>
  <div class="three">
    基本设置
    <button class="topLeft" @click="change">{{buttonText}}</button>


    <el-form size="large"
        style="max-width: 50vw"
        :model="sizeForm"
        label-width="auto"
        :label-position="labelPosition"
    >
      <el-form-item label="昵称">
        <el-input v-if="isState" v-model="sizeForm.nickname" />
      </el-form-item>
      <div class="writeText" v-if="!isState">{{sizeForm.nickname}}</div>
      <hr>
      <el-form-item label="个性签名">
        <el-input v-if="isState" v-model="sizeForm.signature" maxlength="15" show-word-limit></el-input>
      </el-form-item>
      <div class="writeText" v-if="!isState">{{sizeForm.signature}}</div>
      <hr>
      <el-form-item label="性别">
        <el-radio-group  v-if="isState" v-model="sizeForm.gender">
          <el-radio value="-1">保密</el-radio>
          <el-radio value="1">男</el-radio>
          <el-radio value="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <div class="writeText" v-if="!isState">{{Sex}}</div>
      <hr>
      <el-form-item label="简介">
        <el-input v-if="isState" v-model="sizeForm.introduction" type="textarea" maxlength="100" show-word-limit autosize></el-input>
      </el-form-item>
      <div class="writeText" v-if="!isState">{{sizeForm.introduction}}</div>
      <hr>
    </el-form>

  </div>
</template>

<style scoped>
.topLeft{
  position: relative;
  bottom: 2vh;
  margin-left: 40vw;
  border: none;
  background-color: #f5f5f5;
  color: red;
  font-size: 3vh;
}
.topLeft:hover{
  cursor: pointer;
}
.topLeft:active{
  color: #0056b3;
}
.three{
  padding-top: 3vh;
  padding-right: 3vh;
  padding-left: 3vh;
  font-size: 3vh;
  width: 100%;
  max-height:100vh ;
  color: black;
}
.writeText{
  padding-left: 2.5vw;
  background-color: #f5f5f5;
  font-size: 2vh;
}
.el-form-item--large{
  --font-size: 2.5vh;
  margin-bottom: 1vh;
  margin-top: 1.5vh;
  color: #181818;
}
.el-form{
  margin-top: -1.5vh;
  width: 60vw;
}
.el-input--large{
  font-size: 2.5vh;
}
.el-input--large{
  border: none;
  outline: none;
}
.el-input__wrapper{
  border: none;
  outline: none;
}
.el-radio__label{
  font-size: 2vh;
}

:root{
  --el-text-color-regular:black;
}

</style>
