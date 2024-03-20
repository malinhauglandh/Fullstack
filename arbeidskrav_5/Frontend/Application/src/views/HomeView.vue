<template>
  <div v-if="!login" class="login">
    <Login />
  </div>

  <div v-else class = "home">
    <h1>Welcome to my App</h1>
    <div class="logout">
      <img id="image" src="../assets/icons/cat.png"/>
      <button class="btn" @click="logout">Logout</button>
    </div>
  </div>
</template>

<script setup>
import Login from '@/components/Login.vue'
import { useStore } from '../store/store.js'
import { ref } from 'vue'

const store = useStore()
const login = ref(store.loggedInUser !== null)

function logout() {
  console.log("logout")
  store.loggedInUser = null
  store.jwtToken = null
  login.value = false
}
</script>

<style scoped>

h1 {
  font-size: 50px;
  text-align: center;
  color: #9f825a;
}

image {
  width: 100px;
  height: 100px;
  align-content: center;
}

.logout {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}

.btn {    
  background-color: white;
  border-radius: 15px;
  border: 2px solid #9f825a;
  cursor: pointer;
  padding: 15px;
  color: #9f825a;
  text-align: center;
  font-weight: bold;
}
.btn:hover {
  background-color: #9f825a;
  color:white;
}

</style>