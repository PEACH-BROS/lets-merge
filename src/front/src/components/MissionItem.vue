<template>
    <tr>
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
            <p
                    @click="showMembers"
                    class="text-gray-900 whitespace-no-wrap"
            >{{mission.memberCount}}</p>
        </td>
    </tr>

</template>

<script>
    export default {
        name: "MissionItem",
        props: {
            mission: {
                type: Object,
                required: true
            }
        },
        methods: {
            showMembers() {
                alert("모달");
            }
        }
    }
</script>

<style scoped>

</style>