<template>
  <div class="login-wrapper">
  <h1>Login</h1>
  <form id="login-form" @submit.prevent="login">
    <div class="usernameInput">
      <label for="loginUsername">Username:</label>
      <input type="text" name="username" id="loginUsername" placeholder="username" v-model="username">
    </div>
    <div class="passwordInput">
      <label for="loginPassword">Password:</label>
      <input type="password" name="password" id="loginPassword" placeholder="password" v-model="password">
    </div>
    <button class="btn" type="submit" :disabled="password.length === 0 || username.length === 0">{{ header }}</button>
  </form>
  <div class="new-user-button">
    <button class="btn" @click="displaySignUp">{{ buttonGuide }}</button>
  </div>
  <div class="errorMessage" v-if="showError">
    <p>{{ errorMessage }}</p>
  </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import axios from 'axios'
import { router } from '../router/index.js'
import { useStore } from '../store/store.js'

const username = ref("")
const password = ref("")
const signUp = ref(false)
const header = ref("Login")
const buttonGuide = ref("Click here to create a new account")
const showError = ref(false)
const errorMessage = ref("Something went wrong")
const store = useStore()


async function login() {
  console.log("login")
  let path = "http://localhost:8080/"
  if(signUp.value){
    path += "createUser"
  } else {
    path += "token"
  }
  var user = {
    username: username.value,
    password: password.value
  }
  const config = {
    headers: {
      "Content-type": "application/json",
    },
  };
  await axios.post(path, user, config).then(response => {
    let data = response.data;
    if(data) {
      showError.value = false
      if(!signUp.value){
        store.saveToken(username.value, response.data)
        store.loggedInUser = {
          username: username.value
        }
        router.push('/calculator')
      }
    }
  }).catch(error => {
    console.error(error)
    showError.value = true
    errorMessage.value = error.response.data
  })
}

function displaySignUp() {
  if(!signUp.value){
    signUp.value = true
    header.value = "Create account"
    buttonGuide.value = "Click here to login"
  } else {
    signUp.value = false
    header.value = "Login"
    buttonGuide.value = "Click here to create a new account"
  }
}

function refreshToken(){
  let path = "http://localhost:8080/token"
  var user = {
    username: username.value,
    password: password.value
  }
  const config = {
    headers: {
      "Content-type": "application/json",
    },
  };
  setInterval(async () => {
    console.log("refreshed token")
    await axios.post(path, user, config).then(response => {
      let data = response.data;
      if(data) {
        saveToken(username.value, response.data)
      }
    }).catch(error => {
      console.error(error)
    })
  }, 1000 * 60 * 5)
}

watch(() => store.jwtToken, () => {
  if(store.loggedInUser.value !== null){
    refreshToken()
  }
})
</script>

<style scoped>

.login-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-top: 5%;
  width: 100%
}

#login-form{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  padding: 1em;
  margin-top: 2em;
  border: 1px solid #9f825a;
  border-radius: 10px;
}

#login-form label {
  color: #9f825a;
  font-size: 20px;
  margin-bottom: 5px;
}

.new-user-button {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 5%;
}

.new-user-button:disabled {
  background-color: transparent;
  border: 2px solid darkgray;
  color: black;
  cursor: not-allowed;
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

h1 {
  margin: 0;
  font-size: 50px;
  text-align: center;
  color: #9f825a;
}

input {
  width: 100%;
  padding: 10px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box; 
}

.signUp{
  margin: 20px;
}


.usernameInput, .passwordInput {
  margin: 10px 0;
}


</style>
