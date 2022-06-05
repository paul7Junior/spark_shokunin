<template>
      <p>EXPLORER LIST</p>
      <Markdown :source="source" />
      <Markdown :source="source2" />


</template>

<script>
import Markdown from 'vue3-markdown-it';
import axios from 'axios';
import { NButton } from 'naive-ui'

export default {
  components: {
    Markdown,
    NButton
  },
  name: 'Explorer',
  props: {
    msg: String
  },
  data() {
    return {
      source: '# Hello World\n ```js\n console.log("Hello World! This is Python.")\n```',
      source2: ''
    }
  },
  mounted () {
    axios
      .get('http://localhost:8082/elastic')
      .then(response => {console.log(this.info = response); this.source2 = response.data.map(x=>x.content).join(' ')})
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
