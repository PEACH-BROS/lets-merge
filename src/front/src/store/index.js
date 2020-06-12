import Vue from 'vue';
import Vuex from 'vuex';
import mission from '@/store/modules/mission'
import category from '@/store/modules/category'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        mission,
        category
    }
})