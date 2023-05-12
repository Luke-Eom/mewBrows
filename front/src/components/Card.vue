<template>

              <div class="card shadow-sm" >
                <span class="img" :style="{backgroundImage: `url(${item.imgPath})`}" />
                <div class="card-body">
                  <p class="card-text">{{ item.name }}</p>
                  <div class="d-flex justify-content-between align-items-center">
                   <span class="discount badge bg-danger">할인중</span>
                   <div class="btn-group">
                     <button type="button" class="btn btn-sm btn-outline-secondary">예약</button>
                     <button type="button" class="btn btn-sm btn-outline-secondary" @click="pageLink()">시간표 확인</button>
                   </div>
                   <small class="text-muted">리뷰 보기</small>
                  </div>
                </div>
              </div>
</template>

<script>
  export default{
    name: "Card",
    props: {
    item: Object
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