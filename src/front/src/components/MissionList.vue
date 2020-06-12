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
            <MissionItem
                    :key="mission.id"
                    :mission="mission"
                    v-for="mission in missions"/>
            </tbody>
        </table>
    </div>
</template>

<script>
    import {mapState} from 'vuex';
    import MissionItem from "./MissionItem";

    export default {
        name: "MissionList",
        components: {
            MissionItem,
        },
        computed: {
            ...mapState('mission', ['missions']),
        },
    }
</script>

<style scoped>
</style>