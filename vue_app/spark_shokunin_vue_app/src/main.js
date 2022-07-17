import { createApp } from 'vue'
import { createStore } from 'vuex'

import App from './App.vue'
import Markdown from 'vue3-markdown-it';
import 'highlight.js/styles/monokai.css';
import 'highlight.js/styles/paraiso-light.css';

import naive from "naive-ui";
import axios from 'axios';

const app = createApp(App)

const store = createStore({
    state () {
      return {
        count: 0,
        searchValue: "",
        searchResult: ""
      }
    },
    mutations: {
      increment (state) {
        state.count++
      },
      updateMessage (state, searchValue) {
          console.log("sssss")
        state.searchValue = searchValue
      },
      updateSearchResult (state, searchResult) {
          console.log('commit update searchresult')
          state.searchResult = searchResult
          console.log(state.searchResult)
      }
    },
    actions: {
        callApi ({ commit, state }) {
            console.log("sssss")
            axios
            .post('http://localhost:8082/search', {
                  value: state.searchValue
                })
            .then(response => {console.log("FOLLOW THE FAT RABBIT")
              console.log(response); 
              commit('updateSearchResult', response)
            })
        }
      }
  })

app.use(naive).use(Markdown).use(store).mount('#app')

// app.component('Button', Button);

// app.use(Button).mount('#app');
// app.config.globalProperties.$message = message;






