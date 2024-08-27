import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from "./router";
import axios from "axios";
axios.defaults.baseURL = 'http://127.0.0.1:8080';

const app = createApp(App)
app.use(ElementPlus).use(router).use(store)
app.mount('#app')
