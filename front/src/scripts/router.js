import Home from "@/pages/Home";
import Login from "@/pages/loginViews/Login";

import {createRouter, createWebHistory} from "vue-router/dist/vue-router";

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    { path: '/kakao-login', name: 'KakaoLogin', component: () => import('@/pages/loginViews/KakaoLogin') },
    { path: '/naver-login', name: 'NaverLogin', component: () => import('@/pages/loginViews/NaverLogin') },
    { path: '/google-login', name: 'GoogleLogin', component: () => import('@/pages/loginViews/GoogleLogin') },
    { path: '/admin', name: 'Dashboard', component: () => import('@/pages/admin/Dashboard')},
    { path: '/grid-system', name: 'GridSystem', component: () => import('@/pages/admin/GridSystem')},
]

const router = createRouter({
    history: createWebHistory(),
    routes,
    // eslint-disable-next-line no-unused-vars
    scrollBehavior(to, from, savedPosition) {
        return { x: 0, y: 0 }
    }

})

export default router;