<template>
  <v-app class="app">
    <AppBar />
    <router-view class="router-view" />
    <v-snackbar top timeout="1300" class="snackbar mt-16" v-model="snackbarVisible">
      {{ snackbarMessage }}
    </v-snackbar>
  </v-app>
</template>

<script>
import AppBar from "@/components/AppBar";

export default {
  name: "App",
  components: {
    AppBar,
  },
  computed: {
    snackbarMessage: {
      get() {
        return this.$store.getters["snackbar/message"];
      },
      set() {
        this.$store.commit("snackbar/HIDE");
      },
    },
    snackbarVisible: {
      get() {
        return this.$store.getters["snackbar/visible"];
      },
      set() {
        this.$store.commit("snackbar/HIDE");
      },
    },
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

.snackbar {
  z-index: 10000 !important;
}
</style>
