<template>
  <v-dialog v-model="ScheduleDialogVisible" max-width="500" persistent>
    <template v-slot:activator>
      <v-btn class="btn btn-sm btn-outline-secondary" @click="openDialog">{{ item.menu}}</v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="headline">예약 신청</span>
      </v-card-title>
      <v-card-text>
        <v-text-field v-model="name" label="Name"></v-text-field>
        <v-text-field v-model="email" label="Email"></v-text-field>
        <v-text-field v-model="phoneNumber" label="Phone Number"></v-text-field>
        <v-text-field v-model="surgeryType" label="Surgery Type"></v-text-field>
        <v-text-field v-model="scheduleTime" label="Time"></v-text-field>
      </v-card-text>
      <v-card-actions class="d-flex justify-content-center">
        <v-btn color="primary" text @click="closeScheduleDialog">Cancel</v-btn>
        <v-btn color="primary" @click="submitForm">Submit</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-dialog
      v-model="requestSent"
      width="auto"
  >
    <v-card>
      <v-card-title>
        <span>예약 요청이 완료되었습니다!</span>
      </v-card-title>
      <v-card-actions class="d-flex justify-content-center">
        <v-btn
            color="primary"
            variant="text"
            @click="requestSent = false"
            class="justify-center"
        >
          확인
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import ScheduleDialog from "./ScheduleDialog.vue";
import axios from "axios";

export default {
  computed: {
    ScheduleDialog() {
      return ScheduleDialog
    }
  },
  props: ['item'],
  data() {
    return {
      ScheduleDialogVisible: false,
      requestSent: false,
      name: '',
      email: '',
      phoneNumber: '',
      surgeryType: '',
      scheduleTime: ''
    };
  },
  methods: {
    openDialog() {
      if (this.item.menu === "예약") {
        this.ScheduleDialogVisible = true;
      }
    },
    closeScheduleDialog() {
      this.ScheduleDialogVisible = false;
    },
    submitForm() {

      const formData = {
        name: this.name,
        email: this.email,
        phoneNumber: this.phoneNumber,
        surgeryType: this.surgeryType, // 선택 탭 만들고 해당 시술 타입 마다 enum에 맞는 인데스값
        time: this.time
      }

      //추 후 관리자 생성 요청 | 유저 요청으로 나누기 (이부분은 유저 요청에 해당 그러므로 user요청 url로 바꾸자)
      axios.post("http://localhost:8080/api/schedule/create-schedule-by-admin", formData)
          .then(response => {
            console.log(response.data);
            //요청 완료 후 추가 action
            this.ScheduleDialogVisible = false;
            this.requestSent = true;
          })
          .catch(error => {
            console.error(error);
            // 조건 내 동일인 중복 요청시 ? db 메모리
            // 중복 요청이 있습니다 다시 확인해주세요 etc.
          });

      console.log('form submitted successfully');
    }
  }

};
</script>

<style scoped>
.headline {
  font-size: 24px;
}
</style>