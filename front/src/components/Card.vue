<template>
              <div class="card shadow-sm">
                <span class="img" :style="{backgroundImage: `url(${item.imgUrl})`}" />
                <div class="card-body">
                  <div class="d-flex justify-content-between align-items-center">
                    <button type="button" class="btn btn-sm btn-outline-secondary" align="center" @click="pageLink()">{{ item.menu}}</button>
                  </div>
                </div>
              </div>
</template>

<script>
  export default{
    name: "Card",
    props: {
      item : {
        type: Object,
        required: true
      }
    }
  }

</script>
<script setup>
import { useRouter } from "vue-router";
  const router = useRouter();
  const pageLink = () => {
    router.push({path: 'schedule'})
  }
</script>
<style scoped>
.card .img {
    display: inline-block;
    width: 100%;
    height:250px;
    background-size: cover;
    background-position: center;
}

</style>