<script setup>
import Calculator from './components/Calculator.vue'
</script>

<template>
  <header>
    <div>
      <h1> Calculator</h1>
      <Calculator msg="Calculator" />
    </div>
  </header>

  
</template>

<style>

h1 {
  font-size: 50px;
  text-align: center;
  color: #9f825a;
}

html, body {
  background-color: #f2eee8;
  margin: 0;
  padding: 0;
  font-family: 'Times New Roman', Times, serif;
}

</style>
