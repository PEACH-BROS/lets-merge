<template>
  <v-app-bar fixed flat max-height="55" color="grey darken-3">
    <router-link to="/" class="text-decoration-none">
      <v-icon dark class="mr-3">mdi-home</v-icon>
    </router-link>
    <v-toolbar-title style="color: white;">이만 머지할게요 💯</v-toolbar-title>
    <v-spacer />
    <v-btn v-if="loginUser" outlined dark to="/mypage">마이페이지</v-btn>
    <v-btn v-else outlined dark>Github 로그인</v-btn>
  </v-app-bar>
</template>

<script>
export default {
  name: "appBar",
  data() {
    return {
      loginUser: false,
    };
  },
};
</script>

<style scoped></style>
