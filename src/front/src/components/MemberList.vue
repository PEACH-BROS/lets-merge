<template>
    <div>
        <table class="min-w-full leading-normal">
            <thead>
            <tr>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    회원 번호
                </th>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    이름
                </th>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    이메일
                </th>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    프로필
                </th>
            </tr>
            </thead>
            <tbody>
            <tr :key="member.id" v-for="member in members">
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{member.id}}</p>
                </td>
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{member.name}}</p>
                </td>
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{member.email}}</p>
                </td>
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{member.profile}}</p>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import {mapState} from 'vuex';

    export default {
        name: "memberList",
        computed: {
            ...mapState('member', ['members']),
        },
        methods: {}
    }
</script>

<style scoped>
</style>