<template>
  <v-app class="app">
    <AppBar />
    <router-view class="router-view" />
  </v-app>
</template>

<script>
import AppBar from "@/components/AppBar";

export default {
  name: "App",
  components: {
    AppBar,
  },
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
