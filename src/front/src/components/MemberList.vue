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
            <MemberItem
                    :key="member.id"
                    :member="member"
                    v-for="member in members"
            />
            </tbody>
        </table>
    </div>
</template>

<script>
    import {mapState} from 'vuex';
    import MemberItem from "./MemberItem";

    export default {
        name: "memberList",
        components: {
            MemberItem
        },
        computed: {
            ...mapState('member', ['members']),
        },
        methods: {}
    }
</script>

<style scoped>
</style>