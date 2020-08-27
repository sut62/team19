import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../components/login'
import menu from '../components/menu'
import signup from '../components/signup'

import character from '../components/character'
import characterList from '../components/characterList'



Vue.use(VueRouter)

export default new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            component: login
        },
        {
            path: '/menu',
            component: menu
        },
        {
            path: '/signup',
            component: signup
        },
        {
            path: "/characterList",
            name: "characterList",
            component: characterList,
        },
        {
            path: "/character/:id?",
            name: "character-details",
            component: character,
            props: true
        }
    ]
})