<template>
  <v-dialog v-model="ScheduleDialogVisible" max-width="500">
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
        <v-text-field v-model="time" label="Time"></v-text-field>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" text @click="closeScheduleDialog">Cancel</v-btn>
        <v-btn color="primary" @click="submitForm">Submit</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import ScheduleDialog from "./ScheduleDialog.vue";

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
      name: '',
      email: '',
      phoneNumber: '',
      surgeryType: '',
      time: ''
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
      // Here you can perform any actions with the form data (e.g., submit to a server)
      // Access the form values using this.name, this.email, this.phoneNumber, etc.
      console.log('Form submitted');
      this.ScheduleDialogVisible = false;
    }
  }

};
</script>

<style scoped>
.headline {
  font-size: 24px;
}
</style>