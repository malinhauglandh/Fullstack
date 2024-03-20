<template>
    <CalculationHistory :pageNumber="pageNumber"
                        :morePages="morePages"
                        :calculations="calculations"
                        v-if="notEmptyList"
                        @nextPage="nextPage"
                        @prevPage="prevPage" />
</template>

<script setup>
import { ref, watch, onMounted } from 'vue';
import axios from 'axios';
import CalculationHistory from '../components/CalculationHistory.vue';
import { useStore } from '../store/store.js';

const apiUrl = 'http://localhost:8080/calculations/'; 
const pageNumber = ref(0); 
const calculations = ref([]);
const morePages = ref(false); 
const notEmptyList = ref(false); 
const store = useStore();


const username = ref(store.loggedInUser.username); 

const fetchCalculations = async () => {
  try {
    const path = apiUrl + username.value + '/0';
    const response = await axios.get(path, {
      headers: {
        Authorization: `Bearer ${store.jwtToken}`,
      },
    });
    calculations.value = response.data;
    notEmptyList.value = calculations.value.length > 0;
  } catch (error) {
    console.error('Error fetching calculations:', error);
  }
};

onMounted(fetchCalculations);

watch(pageNumber, fetchCalculations);

const nextPage = () => {
  pageNumber.value += 1;
};

const prevPage = () => {
  if (pageNumber.value > 0) pageNumber.value -= 1;
};
</script>
