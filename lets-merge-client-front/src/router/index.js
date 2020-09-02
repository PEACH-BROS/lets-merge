import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home";
import MyPage from "@/views/MyPage";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
