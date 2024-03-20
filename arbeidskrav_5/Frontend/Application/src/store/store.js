import { defineStore } from "pinia";
import { ref } from "vue";

export const useStore = defineStore("token", () => {
  const jwtToken = ref(null);
  const loggedInUser = ref(null);
  const email = ref('');
  const name = ref('');
  const feedback = ref('');


  function saveToken(username, token) {
    loggedInUser.value = username;
    jwtToken.value = token;
  }

  function clearToken() {
    jwtToken.value = null;
    loggedInUser.value = null;
  }

  return { jwtToken, loggedInUser, saveToken, clearToken, email, name, feedback};
}, 
{
  persist: {
    storage: sessionStorage,
    enabled: true
  }
});