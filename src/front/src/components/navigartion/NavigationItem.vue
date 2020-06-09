<template>
    <div>
        <router-link :to="category.path">
            <button
                    :class="category.selected ? 'text-pink-400 border-b-2 font-medium border-pink-400' : ''"
                    @click="selectCategory"
                    class="text-gray-600 py-4 px-6 block hover:text-pink-400 focus:outline-none">
                {{category.name}}
            </button>
        </router-link>
    </div>
</template>

<script>
    export default {
        name: "NavigationItem",
        props: {
            category: {
                type: Object,
                required: true
            }
        },
        methods: {
            selectCategory() {
                this.$store.dispatch('category/selectCategory', this.category.name);
            }
        }
    }
</script>

<style scoped>

</style>