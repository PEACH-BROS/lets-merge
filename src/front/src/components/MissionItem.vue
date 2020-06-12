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
            >{{mission.members.length}}</p>
        </td>
        <Modal @close="showModal = false"
               v-if="showModal"
        >
            <template v-slot:header>
                참여 중인 사용자
            </template>

            <template v-slot:body>
                <MemberItem
                        :key="member.id"
                        :member="member"
                        v-for="member in mission.members"
                />
            </template>
        </Modal>
    </tr>

</template>

<script>
    import Modal from "./Modal";
    import MemberItem from "./MemberItem";

    export default {
        name: "MissionItem",
        components: {
            Modal,
            MemberItem
        },
        data() {
            return {
                showModal: false
            }
        },
        props: {
            mission: {
                type: Object,
                required: true
            }
        },
        methods: {
            showMembers() {
                this.showModal = true;
            },
        }
    }
</script>

<style scoped>

</style>