<template>
    <div class="contact-wrapper">
      <h1>Contact</h1>
      <form id="contact-form" @submit.prevent>
        <label for="name">Name</label>
        <input id="name" type="text" placeholder="Name" v-model="inputName" @change="updateStore" required>
        
        <label for="email">Email</label>
        <input id="email" type="email" placeholder="Email" v-model="inputEmail" @change="updateStore" required>

        <label for="feedback">Feedback</label>
        <textarea id="feedback" type="text" placeholder="Feedback" v-model="inputFeedback" @change="updateStore" required></textarea>
        
        <div class="form-button">
          <button class="btn" @click="handleClick" :disabled="!validInput" id="submit-button">Submit</button>
          <!-- Icon displayed when form is submitted-->
          <span v-if="submitted" class="icon">
            <font-awesome-icon id="submit-success" icon="check" /></span>
        </div>
      </form>
    </div>
</template>



<script setup>
import { ref, computed } from 'vue'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { useStore } from '../store/store.js'
import axios from 'axios'

const store = useStore();
const inputName = ref(store.name);
const inputEmail = ref(store.email);
const inputFeedback = ref(store.feedback);
let submitted = false;

const validInput = computed(() => {
  return inputName.value.length > 1 && inputEmail.value && inputFeedback.value.length > 2;
});

function updateStore() {
  store.name = inputName.value;
  store.email = inputEmail.value;
  store.feedback = inputFeedback.value;
}

function handleClick() {
    if(!validInput.value) return;

    const feedback = {
      name: inputName.value,
      email: inputEmail.value,
      feedback: inputFeedback.value
    };
    submitData(feedback);
    submitted = true;
}

function submitData(feedback) {
  axios.post('http://localhost:3000/feedbacks', feedback)
  .then(response => {
    console.log("Feedback submitted: ", response.data);
    inputName.value = '';
    inputEmail.value = '';
    inputFeedback.value = '';
  })
  .catch(error => {
    console.log("Error submitting feedback: ", error);
  });
}

</script>

<style scoped>

.contact-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-top: 5%;
  width: 100%
}

#contact-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 60%;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  padding: 1em;
  margin-top: 2em;
  border: 1px solid #9f825a;
  border-radius: 10px;
}

#contact-form label {
  color: #9f825a;
  font-size: 20px;
  margin-bottom: 5px;
}

.form-button {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 5%;
}

.form-button button:disabled {
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

textarea {
  width: 100%;
  padding: 10px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  resize: none;
}

.icon {
  margin-left: 10px;
}

</style>