<template>
    <main>
      <div class="navigate">
        <button id="prev-btn" v-if="pageNumber > 0" @click="$emit('prevPage')">Previous</button>
        <p v-else></p>
        <h2>History</h2>
        <button id="next-btn" @click="$emit('nextPage')" v-if="morePages">Next</button>
      </div>
      <div class="history">
        <p v-for="calculation in calculations" @click="$emit('historyclick', calculation)" class="calculation">
          {{ calculation }}
        </p>
      </div>
    </main>
  </template>
  
  <script setup>
  import { defineProps } from 'vue';
  
  const props = defineProps({
    calculations: {
      type: Array,
      required: true
    },
    pageNumber: {
      type: Number,
      required: true
    },
    morePages: {
      type: Boolean,
      default: false
    }
  });
  </script>
  
  <style scoped>
  main {
    margin-top: 10px;
    padding: 0 5px;
    border-radius: 10px;
    min-width: 324.33px;
  }
  .history {
    display: grid;
    grid-template-columns: 1fr 1fr;
    column-gap: 10px;
    justify-items: center;
    align-items: center;
  }
  h2 {
    grid-column: 1 / 5;
    text-align: center;
    font-size: 30px;
    color: #9f825a;
  }
  
  p {
    width: fit-content;
    justify-items: center;
    font-size: 20px;
  }
  
  .navigate {
    display: grid;
    width: 100%;
    grid-template-columns: 1fr 1fr 1fr;
  }
  
  .navigate button {
    align-self: center;
    margin-left: auto;
    height: 20px;
    width: 70px;
  }
  
  .navigate button:nth-child(odd) {
    margin-right: auto;
  }
  
  .navigate button {
    background-color: cadetblue;
    border: 1px darkslateblue;
    border-style: ridge;
    color: white;
  }
  
  .navigate button:hover {
    background-color: darkcyan;
  }
  </style>
  