<template>
  <div class="explorer">
      <p>EXPLORER LIST</p>
      <Markdown :source="source" />
  </div>
</template>

<script>
import Markdown from 'vue3-markdown-it';

export default {
  components: {
    Markdown
  },
  name: 'Explorer',
  props: {
    msg: String
  },
  data() {
    return {
      source: '# Hello World\n ```js\n console.log("Hello World! This is Python.")\n```'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>