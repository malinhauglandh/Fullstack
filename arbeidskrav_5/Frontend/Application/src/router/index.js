import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CalculatorView from '../views/CalculatorView.vue'
import ContactView from '../views/ContactView.vue'
import LoginView from '../views/LoginView.vue'
import HistoryView from '../views/HistoryView.vue'

export const router = createRouter({
    history: createWebHistory(),
    routes: [
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
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
    {
        path: '/history',
        name: 'history',
        component: HistoryView
    }
]})


