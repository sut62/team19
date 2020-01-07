import Vue from 'vue'
import VueRouter from 'vue-router'
import RentCar from '../components/RentCar'
import Hello from '../components/ChoiceLogin'
import loginm from '../components/loginm'
import login from '../components/login';
import menu from '../components/menu';

Vue.use(VueRouter)

export default new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: Hello
        },
        {
            path: '/login',
            component: login
        },
        {
            path: '/loginm',
            component: loginm
        },
        {
            path: '/menu',
            component: menu
        },
        {
            path: '/rentcar',
            component: RentCar
        },
    ]
})