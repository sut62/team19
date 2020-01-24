import Vue from 'vue'
import VueRouter from 'vue-router'
import RentCar from '../components/RentCar'
import Car from '../components/Car'
import Customer from '../components/Customer'
import ViewCustomer from '../components/ViewCustomer'
import Return from '../components/ReturnCar'
import ReturnData from '../components/ReturnCardata'
import Hello from '../components/ChoiceLogin'
import loginm from '../components/loginm'
import login from '../components/login'
import menu from '../components/menu'
import Maintenance from '../components/Maintenance'
import Payment from '../components/Payment'
import ViewDataPayment from '../components/ViewDataPayment'
import ViewRental from '../components/ViewDataRental'
import CarData from '../components/CarData'
import MaintenanceOptions from '../components/MaintenanceOptions'
import MaintenanceHistory from '../components/MaintenanceHistory'


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
        {
            path: '/car',
            component: Car
        },
        {
            path: '/maintenance',
            component: Maintenance
        },
        {
            path: '/cus',
            component: Customer
        },
        {
            path: '/returncar',
            component: Return
        },
        {
            path: '/viewredata',
            component: ReturnData
        },
        {
            path: '/pay',
            component: Payment
        },
        {
            path: '/viewpay',
            component: ViewDataPayment
        },
        {
            path: '/viewdatarent',
            component: ViewRental
        },
        {
            path: '/cardata',
            component: CarData
        },
        {
            path: '/viewcustomer',
            component: ViewCustomer
        },
        {
            path: '/Maintenance_Options',
            component: MaintenanceOptions
        },
        {
            path: '/Maintenance_History',
            component: MaintenanceHistory
        }
    ]
})