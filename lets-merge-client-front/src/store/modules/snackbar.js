export default {
  namespaced: true,
  state: {
    message: "",
    visible: false,
  },
  mutations: {
    SHOW(state, message) {
      state.message = message;
      state.visible = true;
    },
    HIDE(state) {
      state.message = "";
      state.visible = false;
    }
  },
  getters: {
    message(state) {
      return state.message;
    },
    visible(state) {
      return state.visible;
    },
  },
};
