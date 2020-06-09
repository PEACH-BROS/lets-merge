<template>
    <div class="bg-white" id="header">
        <nav class="flex flex-col sm:flex-row py-10">
            <NavigationItem
                    :category="category"
                    :key="category.name"
                    v-for="category in adminCategories"
            />
        </nav>
    </div>
</template>

<script>
    import {mapState} from 'vuex';
    import NavigationItem from "./NavigationItem";

    export default {
        name: "NavigationBar",
        components: {
            NavigationItem
        },
        computed: {
            ...mapState('category', ['adminCategories']),
        },
        methods: {}
    }
</script>

<style scoped>

</style>