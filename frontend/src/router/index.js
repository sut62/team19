import Vue from 'vue'
import VueRouter from 'vue-router'
//import RentCar from '../components/RentCar'
//import viewRentCar from '../components/ViewRentalData'
import Hello from '../components/HelloWorld'

Vue.use(VueRouter)

export default new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes:[
    {
            path: '/',
            component: Hello
        },
  ]
})