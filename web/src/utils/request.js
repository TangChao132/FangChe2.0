import axios from "axios";
import querystring from "querystring";
const request=axios.create({
    // 网络请求的公共配置
    baseURL:'http://localhost:3008',
    timeout:5000
})
// 发送数据之前
request.interceptors.request.use(config=>{
    config.headers['Content-Type']='application/x-www-form-urlencoded;charset=UTF-8';
    if(config.method==='post'){
        config.data=querystring.stringify(config.data)
    }
        return config
    // config包含网络请求的所有信息
    },error => {
    return Promise.reject(error);
})
// 获取数据之前
request.interceptors.response.use(response => {
    let res = response.data;
    if(typeof res==='string'){
        res=res?JSON.parse(res):res
    }
    return res;
},
    error => {
     console.log('err'+error)
        return Promise.reject(error)
    })
export default request