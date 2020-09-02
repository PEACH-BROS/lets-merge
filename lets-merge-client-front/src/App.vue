<template>
  <v-app class="app">
    <v-app-bar max-height="50"></v-app-bar>
    <router-view />
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
</style>
