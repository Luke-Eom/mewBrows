<template>
              <div class="card shadow-sm">
                <span class="img" :style="{backgroundImage: `url(${item.imgUrl})`}" />
                <div class="card-body">
                  <div class="d-flex justify-content-between align-items-center">
                    <button type="button" class="btn btn-sm btn-outline-secondary" align="center" @click="pageLink(item.menu)">{{ item.menu}}</button>
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

const pageLink = (path) => {
    router.push({ path });
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

<!-- pageLink(item.menu)
  예약: 모달 띄워서 예약 요청 보내기 -> navbar에서 예약확인
  app.vue에서 state (app에서 예약 모달, 로그인 모달, 전체적인 모달 관리 through modal container 사용)

  //SPA?
  리뷰: 리뷰 페이지로

  Q&A: q&a 페이지로

  -->