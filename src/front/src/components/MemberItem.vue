<template>
    <tr>
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
</template>

<script>
    export default {
        name: "MemberItem",
        props: {
            member: {
                type: Object,
                required: true
            }
        }
    }
</script>

<style scoped>

</style>