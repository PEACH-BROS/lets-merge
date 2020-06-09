import Vue from 'vue';
import Vuex from 'vuex';
import mission from '@/store/modules/mission'
import category from '@/store/modules/category'
import member from '@/store/modules/member'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        mission,
        category,
        member
    }
})