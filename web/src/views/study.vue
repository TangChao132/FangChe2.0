<script lang="ts" setup>

import Tree from "element-plus";
interface Tree {
  label: string
  children?: Tree[]
}

const handleNodeClick = (data: Tree) => {
  console.log(data)

}
const defaultProps = {
  // 默认属性
  children: 'children',
  label: 'label',
}
const data: Tree[] = [
  {
    label: '我的学习',
    children: [
      {
        label: '我的课程',

      },
      {
        label:'我的测评'
      },
      {
        label: '项目培训'
      },
      {
        label: '我的班级'
      },{
        label: '我的认证'
      }
    ],
  },
  {
    label: '我的求职',
    children: [
      {
        label: '我的简历',
      },
      {
        label: '投递职位',
      },
    ],
  },
  {
    label: '我的合同',
  },
  {
    label:"个人设置",
    children:[
      {
        label: '基本设置'
      },
      {
        label: '安全设置'
      }
    ]
  }
]

</script>

<template>
    <h4>hhhhhh</h4>
  <router-link to="/study/zaixian">在线学习</router-link>
  <router-link to="/study/studyPath">学习路径</router-link>
  <el-tree
      style="max-width: 19vw"
      :data="data"
      :props="defaultProps"
      @node-clickclick="handleNodeClick"

  />


</template>

<style scoped>

</style>