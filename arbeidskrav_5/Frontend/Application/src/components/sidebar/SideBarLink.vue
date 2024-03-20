<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import { collapsed } from './state';
import { defineProps } from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'


const props = defineProps({
  link: {
    type: String,
    required: true,
    default: "/"
  },
  icon: {
    type: String,
  }
})

const route = useRoute();
const isActive = computed(() => {
    const activePaths = ['/','/calculator', '/contact', '/history'];
    return activePaths.includes(route.path);
});

</script>


<template>
    <router-link :to="props.link" class="link" :class="{ active: isActive }">
        <font-awesome-icon :icon="props.icon" class="icon" />
        <transition name="fade">
            <span v-if="!collapsed">
            <slot/>
        </span>
        </transition>
    </router-link>
</template>

<style scoped>
.fade-enter-active, .fade-leave-active {
    transition: opacity 0.1 s;
}
.fade-enter, .fade-leave-to {
    opacity: 0;
}


.link {
    display: flex;
    align-items: center;

    cursor: pointer;
    position: relative;
    font-weight: 400;
    user-select: none;

    padding: 0.4em;
    margin-left: 5;
    border-radius: 0.25em;
    height: 1.5em;

    color: white;  
    text-decoration: none; 
}

.link:hover {
    background-color: var(--sidebar-item-hover);
}

.link.active {
    background-color: var(--sidebar-item-active);
}

.icon {
    flex-shrink: 0;
    width: 25px;
    margin-right: 10px;
    aspect-ratio: 1;
    
}

</style>