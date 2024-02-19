import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
  state () {
    return {
      name: "",
      email: "",
      feedback: "",
    }
  },
  mutations: {
    setName (state, name) {
      state.name = name
    },
    setEmail (state, email) {
      state.email = email
    },
    setFeedback (state, feedback) {
      state.feedback = feedback
    }
  }
})

export default store