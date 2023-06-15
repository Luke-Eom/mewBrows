<template>
  <div>
    <v-card>
      <v-layout>
        <v-navigation-drawer
            expand-on-hover
            :class="{ 'expand-on-hover': isHovered }"
            color="teal-accent-3"
            @mouseenter="isHovered = true"
            @mouseleave="isHovered = false"
            rail>
          <v-list>
            <v-list-item
                prepend-avatar="../img/logo.jpg"
                title="엄지혜"
                subtitle="aaa@test.com"
                color="dark"
                class="white-text"
            ></v-list-item>
          </v-list>

          <v-divider class="white-text"></v-divider>

          <v-list density="compact" nav>
            <v-list-item v-for="item in items" :key="item.title" :prepend-icon="item.icon" :title="item.title" class="white-text" @click="pageLink"></v-list-item>
          </v-list>

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

        </v-navigation-drawer>
      </v-layout>
    </v-card>
  </div>
</template>

<script>

export default {
  name: 'SideNavbar',
  components: {},
  data: () => {
    return {
      isHovered: false,
      items: [
        {title: '예약 현황', icon: 'mdi-calendar-month'},
        {title: '회원 관리', icon: 'mdi-account-group'},
        {title: 'Q&A', icon: 'mdi-help-box'},
      ]
    }
  },
  methods: {
    pageLink() {

    },
    loginWithKakao() {
      // Redirect the user to the Kakao login URL
      window.location.href = "http://localhost:8080/login/oauth/kakao";
    },
  }
}
</script>

<style scoped>
.expand-on-hover {
  width: 280px;
  transition: width 0.3s ease;
  z-index: 100;
}

.white-text {
  color: white;
}

</style>