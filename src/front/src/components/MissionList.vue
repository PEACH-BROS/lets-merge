<template>
    <div>
        <table class="min-w-full leading-normal">
            <thead>
            <tr>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    미션 번호
                </th>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    미션 제목
                </th>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    활성화
                </th>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    시작일
                </th>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    종료일
                </th>
                <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-center text-xs text-gray-600 tracking-wider">
                    인원
                </th>
            </tr>
            </thead>
            <tbody>
            <tr :key="mission.id" v-for="mission in missions">
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{mission.id}}</p>
                </td>
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{mission.name}}</p>
                </td>
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{mission.isActive}}</p>
                </td>
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{mission.startDateTime}}</p>
                </td>
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{mission.dueDateTime}}</p>
                </td>
                <td class="px-5 py-5 bg-white text-sm">
                    <p class="text-gray-900 whitespace-no-wrap">{{mission.memberCount}}</p>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import {mapState} from 'vuex';

    export default {
        name: "MissionList",
        computed: {
            ...mapState('mission', ['missions']),
        },
        methods: {}
    }
</script>

<style scoped>
</style>