import Vue from 'vue'
import VueRouter from 'vue-router'
import DefaultContainer from "@/containers/DefaultContainer";
import MissionList from '@/components/MissionList.vue'
import MemberList from '@/components/MemberList.vue'

Vue.use(VueRouter);

const routes = [
    {
        path: '/admin',
        // redirect: '/admin/missions',
        component: DefaultContainer,
        children: [
            {
                path: 'missions',
                name: 'MissionList',
                component: MissionList
            },
            {
                path: 'members',
                name: 'MemberList',
                component: MemberList
            }
        ]
    }


]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router