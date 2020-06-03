import Vue from 'vue';
import Vuex from 'vuex';
import mission from '@/store/modules/mission'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        mission
    }
})