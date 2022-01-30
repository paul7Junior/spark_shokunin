<template>
  <a-anchor>
  <a-row justify="center">
    <a-col :span="20" style="margin-top:15px">
          <a-input size="large" placeholder="large size" />
    </a-col>
  </a-row>
  </a-anchor>

  <a-row justify="center">
    <a-col :span="8">
        <ListExplorer/>
    </a-col>
    <a-col :span="8">
      <Network/>
    </a-col>
  </a-row>

  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Yo Vue.js p"/>

  <a-button type="primary">Primary Button</a-button>
  <a-button>Default Button</a-button>
  <a-button type="dashed">Dashed Button</a-button>
  <a-button type="text">Text Button</a-button>
  <a-button type="link">Link Button</a-button>

</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import ListExplorer from './components/ListExplorer.vue'
import Network from './components/Network.vue'

export default {
  name: 'App',
  components: {
    HelloWorld,
    ListExplorer,
    Network
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 10px;
}
</style>
