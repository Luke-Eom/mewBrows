<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col cols="12" sm="8" md="6">
        <v-card class="pa-5">
          <v-img src="../assets/kakao_login_button.jpeg" height="100" class="mb-5"></v-img>
          <v-btn color="primary" block @click="loginWithKakao">Login with Kakao</v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "KakaoLogin",
  methods: {
    loginWithKakao() {
      // Redirect the user to the Kakao login URL
      window.location.href = "http://localhost:8080/login/oauth/kakao";
    },
  },
};
</script>

<style scoped>
.v-row {
  height: 100vh;
  align-items: center;
}
</style>
