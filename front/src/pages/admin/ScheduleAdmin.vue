<template>
  <div>
<!--  side bar에서 "예약 현황" 클릭시 이 페이지로 넘겨줌-->
<!--    예약 확정/ 대기 / 취소 대기 / 취소 탭-->
<!--    예약 리스트 보여주기 (오늘 날짜 기준 예약 보여줌)-->
<!--    이름 / 생년월일 / 연락처 / 시술 타입     [기록 생성]   (기록이 생성되었으면 기록 보기 버튼으로 바꿈)-->
<!--    &#45;&#45;페이지 네이션 ? 무한 스크롤링?&#45;&#45;-->
    <div>
      <ScheduleDialog />
    </div>

    <div>
      <button v-for="category in categories" :key="category" @click="fetchSchedules(category)">
        {{ category }}
      </button>
    </div>

    <v-data-table :items="schedules" :headers="headers">
      <template v-slot:item="{ item }">
        <td>{{ item.name }}</td>
        <td>{{ item.phoneNumber }}</td>
        <td>{{ item.birthDate }}</td>
        <td>
          <v-btn @click="recordSchedule(item)" color="primary">Record</v-btn>
        </td>
      </template>
    </v-data-table>


  </div>
</template>

<script>
import axios from "axios";
import ScheduleDialog from "../../components/ScheduleDialog.vue";

export default {
  components: {ScheduleDialog},
  data() {
    return {
      categories: ['CONFIRM', 'CONFIRM_WAIT', 'CANCEL_WAIT', 'CANCEL'],
      schedules: [],
      headers: [
        { text: '이름', value: 'name' },
        { text: '전화번호', value: 'phoneNumber' },
        { text: '생일', value: 'birthdate' },
        { text: '기록', value: 'record' },
      ],
    };
  },
  methods: {
    fetchSchedules(category) {
      switch (category) {
        case "CONFIRM":
          axios.get(`/api/schedule/confirmed-schedules`)
              .then(response => {
                this.schedules = response.data;
              })
              .catch(error => {
                console.error(error);
              });
          break;
        case "CONFIRM_WAIT":
          axios.get(`/api/schedule/owait-schedules`)
              .then(response => {
                this.schedules = response.data;
              })
              .catch(error => {
                console.error(error);
              });
          break;
        case "CANCEL_WAIT":
          axios.get(`/api/schedules/xwait-schedules`)
              .then(response => {
                this.schedules = response.data;
              })
              .catch(error => {
                console.error(error);
              });
          break;
        case "CANCEL":
          axios.get(`/api/schedules/xconfirmed-schedules`)
              .then(response => {
                this.schedules = response.data;
              })
              .catch(error => {
                console.error(error);
              });
          break;
        default:
          console.error("Invalid category");
      }
    },
    recordSchedule(schedule) {
      // Perform action for recording the selected schedule
      // You can navigate to a record creation page or show a modal/dialog for recording
      console.log('Recording schedule:', schedule);
    },
  },

}
</script>

<style scoped>

</style>