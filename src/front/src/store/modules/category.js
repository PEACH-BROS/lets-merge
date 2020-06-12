export default {
    namespaced: true,
    state: {
        adminCategories: [
            {name: "미션 목록", path: "/admin/missions", selected: true},
            {name: "회원 목록", path: "/admin/members", selected: false},
        ]
    },
    mutations: {
        SELECT_CATEGORY(state, name) {
            state.adminCategories.forEach(value => value.selected = false);
            const index = state.adminCategories.findIndex(value => value.name === name);
            state.adminCategories[index].selected = true;
        }
    },
    actions: {
        selectCategory({commit}, name) {
            commit('SELECT_CATEGORY', name);
        }
    }

}