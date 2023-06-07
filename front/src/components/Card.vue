<template>
  <div>
<!--    스타일수정 req
   버튼 center
   prepend icon 각 prop value 별로 - backend에서 iconUrl넣어주기
-->
    <v-container>
      <v-card color="dark" elevation="23" class="card">
        <v-img :src = "item.imgUrl"></v-img>
        <div class="d-flex justify-content-center align-items-center my-4">
<!--          <v-btn class="btn btn-sm btn-outline-secondary" @click="openDialog">{{ item.menu}}</v-btn>-->
<!--          추후 예약시 로그인 상태라면 유저정보 넘겨주기 with :selectedItem or selectedUser -> state, prop-->
          <ScheduleDialog v-if="item.menu === '예약'"/>

        </div>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import ScheduleDialog from './ScheduleDialog.vue';
  export default{
    name: "Card",
    props: {
      item : {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        selectedItem: null
      };
    },
    components: {
      ScheduleDialog
    }
  }
</script>
<style scoped>
.card {
  width: 80%;
  height: 60%;
}

.card .img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

@media (max-width: 600px) {
  .card {
    width: 100%;
    height: 40%;
  }
}
</style>

<!-- pageLink(item.menu)
  예약: 모달 띄워서 예약 요청 보내기 -> navbar에서 예약확인
  app.vue에서 state (app에서 예약 모달, 로그인 모달, 전체적인 모달 관리 through modal container 사용)

  //SPA?
  리뷰: 리뷰 페이지로

  Q&A: q&a 페이지로

  -->