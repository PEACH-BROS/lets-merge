<template>
  <v-app class="app">
    <v-app-bar fixed flat max-height="50" color="grey darken-3">
      <v-icon>mdi-home</v-icon>
      <v-toolbar-title>이만 머지할게요</v-toolbar-title>
      <v-spacer />
      <v-btn>마이페이지</v-btn>
      <v-btn>로그인</v-btn>
    </v-app-bar>
    <router-view class="router-view" />
  </v-app>
</template>

<script>
export default {
  name: "App",

  components: {},

  data: () => ({
    //
  }),
};
</script>

<style>
.app {
  width: 100vw;
  height: 100vh;
}
.router-view {
  margin: 10%;
}
</style>
