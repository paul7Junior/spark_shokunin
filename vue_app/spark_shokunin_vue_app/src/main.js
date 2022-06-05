import { createApp } from 'vue'
import App from './App.vue'
import Markdown from 'vue3-markdown-it';
import 'highlight.js/styles/monokai.css';
import 'highlight.js/styles/paraiso-light.css';

import naive from "naive-ui";

const app = createApp(App)

app.use(naive).use(Markdown).mount('#app')

// app.component('Button', Button);

// app.use(Button).mount('#app');
// app.config.globalProperties.$message = message;






