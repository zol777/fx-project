<template>
  <div id="app">
    <Trade v-for="trade in trades" :key="trade.id" :trade="trade"/>
  </div>
</template>

<script>
import axios from "axios";
import Trade from './components/Trade.vue'

export default {
  name: 'App',
  components: {
    Trade
  },
  data() {
    return {
      trades: []
    }
  },
  mounted() {
    axios
        .get("https://localhost:8443/ajax/trade")
        .then(response => (this.trades = response.data.trades))
        .catch(error => console.log(error))
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
  margin-top: 60px;
}
</style>
