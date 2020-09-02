import Vue from "vue";
import Vuex from "vuex";
import missions from "@/store/modules/missions";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    missions,
  },
});
