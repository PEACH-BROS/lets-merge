export default {
    namespaced: true,
    state: {
        categories: [
            {name: "미션 목록", selected: true},
            {name: "회원 목록", selected: false},
        ]
    },
    mutations: {
        SELECT_CATEGORY(state, name) {
            state.categories.forEach(value => value.selected = false);
            const index = state.categories.findIndex(value => value.name === name);
            state.categories[index].selected = true;
        }
    },
    actions: {
        selectCategory({commit}, name) {
            commit('SELECT_CATEGORY', name);
        }
    }

}