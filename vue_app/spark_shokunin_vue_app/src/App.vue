<template>

<!-- <HelloWorld msg="Welcome to Yo Vue.js p"/> -->
<n-grid x-gap="12" :cols="1">
      <n-gi>
        <Search/>
    </n-gi>
</n-grid>

<n-grid x-gap="12" :cols="2">
      <n-gi>
        <ListExplorer/>
      </n-gi>
      <n-gi>
        <Network />
      </n-gi>
</n-grid>


<!-- <HelloWorld msg="Welcome to Yo Vue.js p"/> -->

</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import ListExplorer from './components/ListExplorer.vue'
import Network from './components/Network.vue'
import Search from './components/Search.vue'
// import axios from 'axios';
import { NAffix } from 'naive-ui'


export default {
  name: 'App',
  components: {
    HelloWorld,
    ListExplorer,
    Search,
    Network,
    NAffix
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  margin-top: 10px;
}
</style>
