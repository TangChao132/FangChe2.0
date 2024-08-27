<script setup>

import {onBeforeMount, onMounted, reactive, ref} from "vue";
import router from "@/router/index.js";
import HeaderMenu from "@/components/HeaderMenu.vue";
import axios from "axios";
import {ElMessage} from "element-plus";


const centerDialogVisible = ref(false)

const radio=ref([])

let testContents =reactive([])
onBeforeMount(()=>{

  const test= localStorage.getItem('subject')

  testContents=JSON.parse(test)
  console.log(testContents[0].type)
})

const cancel= async ()=>{
  await router.push('/test')
}
// 点击提交会对题目答案进行判断，出现对了几个，错了几个
// 没做完，不对
const judge=async()=>{
  centerDialogVisible.value=!centerDialogVisible

  let tests=[]
  for(let item of testContents){
    let metaNew={}
    metaNew=item.options
    tests.push(metaNew)
  }
  console.log('news',tests)
  console.log(radio)
  // 判断结果出分

  for(let i=0;i<radio.value.length;i++){
    console.log(radio.value)

  }





  let token=localStorage.getItem('token')
  let id=localStorage.getItem('id')
  console.log('id',id)
  const num = parseInt(id, 10);
  let type=testContents[0].type

  let data=new FormData()
  data.append('uid',num)
  data.append('type',type)
  let res=await axios.post('/api/history/submit',data,{
    headers:{
      Authorization:'bearer '+token
    }
  })
  console.log('res',res)
  if(res.data.code===200){
    ElMessage.success("提交成功")
    await router.push('/test')
  }

  console.log(radio)


}

</script>

<template>
  <HeaderMenu/>
<div style="width: 100vw;padding-top: 12vh;display: flex;justify-content: center">
  <div class="content">
    <div class="example" v-for="(test,index) in testContents" :key="index">
      {{index+1}}、{{test.text}}
      <div>
        <el-radio-group v-model="radio[index]">
          <el-radio v-for="(item,index) in test.options" :label=index>{{item.text}}
          <br>
          </el-radio>

        </el-radio-group>
      </div>
    </div>
    <div class="bottom">
      <button class="button-end" @click="centerDialogVisible = true">取消</button>
      <el-dialog v-model="centerDialogVisible" title="提醒" width="30%" center>
    <span>   确认要退出吗</span>
        <template #footer>
      <span>
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="cancel">确认</el-button>
      </span>
        </template>
      </el-dialog>
      <button class="button-end" @click="judge">提交</button>
    </div>
  </div>
</div>
</template>

<style scoped>
.content{
  padding-top: 5vh;
  width: 60%;
  box-shadow: 0.15vh 0.25vh 0.5vh 0.5vh;
  margin-bottom: 2vh;
  border: 0.1vh solid darkgray;
  border-radius: 1.5vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  .example{
    font-size: 2.5vh;
    margin-bottom: 2vh;
    width: 75%;
    max-height: 25vh;
  }
  .bottom{
    margin-top: 2vh;
    margin-bottom: 3vh;
    width: 57%;
    display: flex;
    justify-content: space-between;
    .button-end{
      width: 12vw;
      height: 5vh;

    }
  }
}
.button-end:hover{
  cursor: pointer;
}
.button-end:active{
  background-color: white;
}
.el-radio-group{
  display: flex;
  flex-direction: column;
  align-items: start;
}
</style>
