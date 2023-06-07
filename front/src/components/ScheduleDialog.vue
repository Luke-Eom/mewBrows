<template>
  <v-dialog v-model="ScheduleDialogVisible" max-width="500" persistent>
    <template v-slot:activator>
      <v-btn class="btn btn-sm btn-outline-secondary" @click="openDialog">예약 신청</v-btn>
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
        <v-dialog v-model="scheduleTimeDialog" max-width="300">
          <template v-slot:activator="{ on }">
            <v-text-field
                v-model="scheduleTime"
                label="예약 시간"
                readonly
                v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="selectedDate"></v-date-picker>
          <v-time-picker v-model="selectedTime" format="24hr"></v-time-picker>
          <v-card-actions>
            <v-btn color="primary" @click="saveScheduleTime">확인</v-btn>
          </v-card-actions>
        </v-dialog>
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
import { VDatePicker } from "vuetify";

export default {
  components:{VDatePicker},
  computed: {
    ScheduleDialog() {
      return ScheduleDialog
    }
  },

  data() {
    return {
      ScheduleDialogVisible: false,
      requestSent: false,
      name: '',
      birthDate: '',
      phoneNumber: '',
      surgeryTypes: ["EYEBROWS", "EYELASH", "SMP"],
      selectedSurgeryType: "",
      scheduleTime: "",
      scheduleTimeDialog: false,
      selectedDate: new Date().toISOString().substr(0, 10),
      selectedTime: null,
    };

  },
  methods: {
    openDialog() {
      this.ScheduleDialogVisible = true;
    },

    closeScheduleDialog() {
      this.ScheduleDialogVisible = false;
    },

    saveScheduleTime() {
      const selectedDateTime = new Date(
          `${this.selectedDate}T${this.selectedTime}`
      );
      this.scheduleTime = selectedDateTime.toLocaleString();

      this.scheduleTimeDialog = false;
    },

    submitForm() {

      const formData = {
        name: this.name,
        birthDate: this.birthDate,
        phoneNumber: this.phoneNumber,
        surgeryType: this.selectedSurgeryType,
        scheduleTime: this.scheduleTime
      }

      //추 후 관리자 생성 요청 | 유저 요청으로 나누기 switch 사용 현재 사용자가 admin|member
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