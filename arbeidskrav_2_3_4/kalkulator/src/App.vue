<script setup>
import { sidebarWidth } from './components/sidebar/state'
import SideBar from './components/sidebar/SideBar.vue'

</script>

<template>
  <SideBar />
  <div :style="{ 'margin-left': `calc(${sidebarWidth} + 1em)` }">
    <router-view />
  </div>
</template>

<style>

html, body {
  background-color: #f2eee8;
  margin: 0;
  padding: 0;
  font-family: 'Times New Roman', Times, serif;
  height: 100vh;
}

</style>
