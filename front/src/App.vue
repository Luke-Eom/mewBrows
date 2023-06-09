<template>
  <SideNavbar/>
  <v-app>
    <v-main>
      <Header/>
      <RouterView/>
      <SideNavbar/>
      <Footer/>
    </v-main>
  </v-app>
<!--  로그인 체크후 role에 따라 side nave bar-->


</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import store from "@/scripts/store";
import SideNavbar from "./components/SideNavbar.vue";

import axios from "axios";
import {watch} from "vue";
import {useRoute} from "vue-router";

export default {
  name: 'App',
  components: {
    SideNavbar,
    Header,
    Footer
  },
  setup() {
    const check = () => {
      axios.get("/account/check").then(({data}) => {
        console.log(data);
        store.commit("setAccount", data || 0);
      })
    };
    const route = useRoute();
    watch(route, () => {
      check();
    })
  }
}
</script>
<style scoped>
.expand-on-hover {
  width: 280px;
  transition: width 0.3s ease;
}

</style>
