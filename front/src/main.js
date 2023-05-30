import { createApp } from 'vue'
import store from "@/scripts/store"
import router from "@/scripts/router";
import App from './App.vue'
import { BootstrapVue3, IconsPlugin } from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import request from './utils/request'
import cookie from '@/utils/cookie.js'
import axios from "axios";

const app = createApp(App);

app.use(store).use(router).use(BootstrapVue3).use(IconsPlugin).mount('#app');

app.config.productionTip = false;
app.config.globalProperties.axios = request;
app.prototype.$cookie = cookie;
axios.defaults.baseURL = "http://localhost:8080";