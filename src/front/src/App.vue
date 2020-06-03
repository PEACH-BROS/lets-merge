<template>
    <div id="app">
        <div id="container">
            <div id="header">
                <span onclick="location.href='https://www.naver.com'">미션 목록</span>
                <span onclick="location.href=''">회원 목록</span>
            </div>
            <div id="body">
                <MissionList/>
            </div>
        </div>
    </div>
</template>

<script>
    import './assets/styles/style.css'
    import MissionList from "./components/MissionList";

    export default {
        name: 'App',
        components: {
            MissionList
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
