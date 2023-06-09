import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import store from "@/scripts/store"
import router from "@/scripts/router";
import './assets/style.css';
import { library } from '@fortawesome/fontawesome-svg-core';
// import font awesome icon component
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { fab } from '@fortawesome/free-brands-svg-icons';

import Vue3DatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

// add icons to the library
library.add(fas, far, fab);


loadFonts()

createApp(App)
  .use(vuetify).use(store).use(router)
    .component('font-awesome-icon', FontAwesomeIcon)
    .component('VueDatePicker', Vue3DatePicker)
    .mount('#app')
