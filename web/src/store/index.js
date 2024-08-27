import {createStore} from "vuex";
// 管理组件之间的状态（集成的公共部分）任何组件都可获取数据
const store=createStore({
    //所有的状态都在这里（数据）
    // state:{
    //         counter:null,
    //     user:{
    //         username:'123',
    //         imgPath:'12312',
    //         account:'987',
    //         identity:'32112312321',
    //         gender:'1',
    //         introduction:''
    //     },
    //     },

// 读取方法 {{$store.state.counter}}
//     另一种读取方式-快捷读取方式：先引入再实现
//     getter(过滤）与state同级
    mutations:{
        handleNodeClick(state,payload){
            state.counter = payload;
        }
    }
})


export default store;