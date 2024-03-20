import { createApp } from 'vue'
import App from './App.vue'
import {router} from './router/index.js'
import '@/assets/main.css'
import piniaPersistedState from "pinia-plugin-persistedstate"

import {createPinia } from 'pinia'
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

const pinia = createPinia();
pinia.use(piniaPersistedState)

library.add(fas)

createApp(App).component('font-awesome-icon', FontAwesomeIcon).use(pinia).use(router).mount('#app')