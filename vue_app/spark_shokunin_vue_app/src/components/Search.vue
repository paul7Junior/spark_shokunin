<template>
  <div class="search" ref="containerRef">
          <n-affix :top="20" :trigger-top="0" :listen-to="() => containerRef" position="fixed" style="width:-webkit-fill-available;padding-left:30px;padding-right:30px">
              <n-space justify="center">
                <n-input @keyup.enter="submit" v-model:value="searchValue" status="warning" placeholder="Search" size="large" style="width:70vw" />
              </n-space>
      </n-affix>
      aaAAAAAAAA
      {{ searchValue }}
      aaAAAAAAAA
  </div>
</template>

<script>
import { NAffix } from 'naive-ui'
import { ref } from "vue";

export default {
  name: 'Search',
  props: {
    msg: String
  },
    components: {
    NAffix
  },
    setup() {
    const containerRef = ref(null);
    return {
      containerRef,
      searchValue: ref(null)
    };
  },


  computed: {
    searchValue: {
        get () {
          return this.$store.state.searchValue
        },
        set (value) {
          this.$store.commit('updateMessage', value)
        }
      }
  },
  methods: {
    submit() {
      this.$store.dispatch('callApi')
    }
  }


}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>