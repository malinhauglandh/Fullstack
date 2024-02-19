import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CalculatorView from '../views/CalculatorView.vue'
import ContactView from '../views/ContactView.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/calculator',
        name: 'calculator',
        component: CalculatorView 
    },
    {
        path: '/contact',
        name: 'contact',
        component: ContactView
    },
]})

export default router

