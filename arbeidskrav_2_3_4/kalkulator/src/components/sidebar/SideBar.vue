<template>
<div class="sidebar" :style="{ width: sidebarWidth}">
  <h1>
    <span v-if= "collapsed">
      <div>
        <font-awesome-icon icon="cat"/>
      </div>
    </span>
    <span v-else>Sidebar Menu</span>
  </h1>

  <SideBarLink @hover="collapsed=false" link="/" icon="house">Home</SideBarLink>
  <SideBarLink link="/calculator" icon="calculator">Calculator</SideBarLink>
  <SideBarLink link="/contact" icon="address-book">Contact</SideBarLink>


  <span class=" collapse-icon" :class="{'rotate-180' : collapsed}" @click="toggleSidebar">
    <font-awesome-icon :icon="collapsed ? 'angle-double-right' : 'angle-double-left'" />
  </span>

</div>
</template>



<script setup>
import { collapsed, toggleSidebar, sidebarWidth } from './state'
import SideBarLink from './SideBarLink.vue';
 

</script>

<style>

:root {
  --sidebar-width: 200px;
  --sidebar-bg-colr: #9f825a;
  --sidebar-item-hover: #9f825a;
  --sidebar-item-active: #9f825a;
}

</style>

<style scoped>

.sidebar {
    color: white;
    background-color: var(--sidebar-bg-colr);

    float: left;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    bottom: 0;
    padding: 0.5em; 

    transition: 0.3s ease;

    display: flex;
    flex-direction: column;
}

.collapse-icon {
  position: absolute;
  bottom: 0;
  padding: 0.75em;
  cursor: pointer;

  color: whitesmoke;

  transition: 0.2s linear;
  transition: transform 0.2s linear;
  transform: rotate(0deg);
  rotate: 0deg;
}

.rotate-180 {
  transition: transform 0.2s linear;
  transform: rotate(-180deg);
  rotate: 180deg;
}


</style>
