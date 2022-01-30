import { createApp } from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue';
import Markdown from 'vue3-markdown-it';

import 'ant-design-vue/dist/antd.css';
import 'highlight.js/styles/monokai.css';
import 'highlight.js/styles/paraiso-light.css';

createApp(App).use(Antd).use(Markdown).mount('#app')


// app.use(Button).mount('#app');
// app.config.globalProperties.$message = message;



