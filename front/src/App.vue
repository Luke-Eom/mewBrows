<template>
  <v-app>
    <v-main>
      <Header/>
      <RouterView/>
      <Footer/>
    </v-main>
  </v-app>
  <v-card>
    <v-layout>
  <v-navigation-drawer
      expand-on-hover
      :class="{ 'expand-on-hover': isHovered }"
      @mouseenter="isHovered = true"
      @mouseleave="isHovered = false"
      rail>
    <v-list>
      <v-list-item
          prepend-avatar="https://randomuser.me/api/portraits/women/85.jpg"
          title="Sandra Adams"
          subtitle="sandra_a88@gmailcom"
      ></v-list-item>
    </v-list>

    <v-divider></v-divider>

    <v-list density="compact" nav>
      <v-list-item v-for="item in items" :key="item.title" :prepend-icon="item.icon" :title="item.title"></v-list-item>
    </v-list>
  </v-navigation-drawer>
    </v-layout>
  </v-card>

</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import store from "@/scripts/store";
import axios from "axios";
import {watch} from "vue";
import {useRoute} from "vue-router";

export default {
  name: 'App',
  components: {
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
  },

  data: () => {
    return {
      isHovered: false,
      items: [
        {title: 'Dashboard', icon: 'mdi-view-dashboard'},
        {title: 'Photos', icon: 'mdi-image'},
        {title: 'About', icon: 'mdi-help-box'},
      ]
    }
  }
}
</script>
<style scoped>
.expand-on-hover {
  width: 280px;
  transition: width 0.3s ease;
}

</style>
