<template>
    <div id="app">
        <NavigationBar/>
        <DefaultContainer/>
    </div>
</template>

<script>
    import '@/assets/styles/style.css';
    import DefaultContainer from "@/containers/DefaultContainer";
    import NavigationBar from "@/components/navigartion/NavigationBar";

    export default {
        name: 'App',
        components: {
            DefaultContainer,
            NavigationBar
        }
    }
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
