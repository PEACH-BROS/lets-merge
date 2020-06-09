<template>
    <div id="app">
        <div id="container">
            <NavigationBar/>
            <div id="body">
                <router-view/>
            </div>
        </div>
    </div>
</template>

<script>
    import './assets/styles/style.css'
    import NavigationBar from "./components/navigartion/NavigationBar";

    export default {
        name: 'App',
        components: {
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
