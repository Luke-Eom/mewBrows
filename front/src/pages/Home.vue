<template>
 <div class="Home">

      <section class="py-1 text-center container" >
        <Carousel/>


        <div class="row py-lg-3">
          <div class="col-lg-6 col-md-8 mx-auto">
            <span class="img" :style="{backgroundImage: `url(img/logo.jpg)`}" />
          </div>
        </div>
      </section>

        <div class="container" >

          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div class="col" v-for="(item, idx) in state.items" :key="idx">
              <Card :item="item"/>
            </div>
          </div>
        </div>
      </div>

</template>

<script>
import Card from "@/components/Card";
import Carousel from "@/components/Carousel";
import axios from "axios";
import { reactive } from "vue";


export default {
  name: 'Home',
  components: {
    Card,
    Carousel
  },
  setup() {
      const state = reactive({
          items: []
      });
      axios.get(`/api/manage/center-menus`).then(({data}) => {
        console.log(data);
        state.items = data;
        });
      return {state};
  }
}
</script>

<style scoped>
.Home {
  position: relative;
}

.Home::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(~@/assets/bgimg.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  opacity: 0.9;
  z-index: -1;
}

.img {
    display: inline-block;
    width: 100%;
    height: 250px;
    object-fit: cover;
    background: no-repeat;
    background-size: contain;
    background-position: center;
}
</style>