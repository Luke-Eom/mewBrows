<template>
  <v-dialog v-model="scheduleDialogVisible" :max-width="500" persistent>
    <template v-slot:activator>
      <v-btn class="btn btn-sm btn-outline-secondary" @click="openScheduleDialog">예약 신청</v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="headline">예약 신청</span>
      </v-card-title>
      <v-card-text>
        <v-text-field v-model="name" label="이름"></v-text-field>
        <v-text-field v-model="birthDate" label="생년월일"></v-text-field>
        <v-text-field v-model="phoneNumber" label="연락처 (핸드폰)"></v-text-field>
        <v-select
            v-model="selectedSurgeryType"
            :items="surgeryTypes"
            label="시술 타입"
        ></v-select>

          <VueDatePicker v-model="scheduleTime" placeholder="예약날짜를 입력해주세요" text-input :text-input-options="textInputOptions" />



      </v-card-text>
      <v-card-actions class="d-flex justify-content-center">
        <v-btn color="primary" text @click="closeScheduleDialog">취소</v-btn>
        <v-btn color="primary" @click="submitForm">예약 요청</v-btn>
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
import VueDatePicker from "@vuepic/vue-datepicker";
import { parseISO, format } from 'date-fns';
import { utcToZonedTime } from 'date-fns-tz';

export default {
  components:{ VueDatePicker },
  computed: {
    ScheduleDialog() {
      return ScheduleDialog
    },

  },

  setup(){



  },

  data() {
    return {
      scheduleDialogVisible: false,
      requestSent: false,
      name: '',
      birthDate: 0,
      phoneNumber: 0,
      surgeryTypes: ["반영구 눈썹", "속눈썹", "SMP 두피"],
      selectedSurgeryType: "",
      scheduleTime: new Date().toISOString().substr(0, 10),

    };

  },

  mounted() {
    const startDate = new Date();
    startDate.setDate(startDate.getDate() + 1);
    this.minDate = startDate.toISOString().substr(0, 10);

    const endDate = new Date();
    endDate.setDate(endDate.getDate() + 29);
    this.maxDate = endDate.toISOString().substr(0, 10);

  },

  methods: {
    openScheduleDialog() {
      this.scheduleDialogVisible = true;
    },

    closeScheduleDialog() {
      this.scheduleDialogVisible = false;
    },

    submitForm() {

      this.requestSent =true;

      const formData = {
        name: this.name,
        birthDate: parseInt(this.birthDate),
        phoneNumber: parseInt(this.phoneNumber),
        surgeryType: this.selectedSurgeryType,
        scheduleTime: new Date(this.scheduleTime)
      };
      if (this.scheduleTime) {
        const selectedDateTime = parseISO(this.scheduleTime);
        const kstDateTime = utcToZonedTime(selectedDateTime, 'Asia/Seoul');
        formData.scheduleTime = format(kstDateTime, "yyyy-MM-dd'T'HH:mm:ss");
      }

      //추 후 관리자 생성 요청 | 유저 요청으로 나누기 switch 사용 현재 사용자가 admin|member
      axios.post("http://localhost:8080/api/schedule/create-schedule-by-admin", formData)
          .then(response => {
            console.log(response.data);
            //요청 완료 후 추가 action
            this.scheduleDialogVisible = false;
            this.requestSent = true;
          })
          .catch(error => {
            console.error(error.response);
            // 조건 내 동일인 중복 요청시 ? db 메모리
            // 중복 요청이 있습니다 다시 확인해주세요 etc.
          });

      console.log('form submitted successfully');
    }
  }

};
</script>

<script setup>
import { ref } from 'vue';

const scheduleTime = ref();
const textInputOptions = ref({
  format: 'MM.dd.yyyy HH:mm',
  timezone: 'Asia/Seoul'
})
</script>

<style scoped>
.headline {
  font-size: 24px;
}
div {
  text-align: center;
}
div.date {
  display: inline-flex;
}
</style>