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
        </v-navigation-drawer>
      </v-layout>
    </v-card>
  </div>
</template>

<script>
export default {
  name: 'SideNavbar',
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

    }
  }
}
</script>

<style scoped>
.expand-on-hover {
  width: 280px;
  transition: width 0.3s ease;
}

.white-text {
  color: white;
}

</style>