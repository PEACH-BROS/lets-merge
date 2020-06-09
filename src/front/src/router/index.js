import Vue from 'vue'
import VueRouter from 'vue-router'
import MissionList from '@/components/MissionList.vue'
import MemberList from '@/components/MemberList.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/missions',
        name: 'MissionList',
        component: MissionList
    },
    {
        path: '/members',
        name: 'Members',
        component: MemberList
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router