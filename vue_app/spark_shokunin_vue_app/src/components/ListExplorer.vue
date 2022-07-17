<template>
      <Markdown :source="source" />
      <Markdown :source="searchResult" />


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
      // source: '# Hello World\n ```js\n console.log("Hello World! This is Python.")\n``` ',
      source: '# Hello World\n  \ fffff \ ddusdusfhidsf ',
      // source2: ''
    }
  },
  computed: {
    searchResult: {
        get () {
          console.log('get listexplorer')
          if (this.$store.state.searchResult !== '')
            return this.formatRawFromElastic(this.$store.state.searchResult)
            else null
        },
        set (value) {
          this.$store.commit('updateMessage', value)
        }
      }
  },
  methods: {
    formatRawFromElastic(raw) {
      var formatted = raw.data.map(x=>"# " + x.path.split("/").at(-1) + "\n" + x.content).join(' ')
      // console.log(raw)
      return formatted
    }
  },
  mounted () {
    // axios
    //   .get('http://localhost:8082/elastic')
    //   .then(response => {console.log(response); 
    //   // this.source2 = response.data.map(x=>x.content).join(' ')
    //   this.source2 = this.formatRawFromElastic(response)
    //   })

    // axios
    //   .post('http://localhost:8082/mypath',{
    //         value: 'Fred'
    //       })
    //   .then(response => {console.log("FOLLOW THE FAT RABBIT")
    //     console.log(response); 
    //   })

      
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
