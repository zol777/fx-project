<template>
  <table id="trades">
    <tr>
      <th id="userId">User ID</th>
      <th class="currency">Currency From</th>
      <th class="currency">Currency To</th>
      <th class="amount">Amount Sell</th>
      <th class="amount">Amount Buy</th>
      <th id="rate">Rate</th>
      <th id="time">Time Placed</th>
      <th id="country">Originating Country</th>
    </tr>
    <tr v-for="trade in trades" :key="trade.id">
      <td>{{ trade.userId }}</td>
      <td>{{ trade.currencyFrom }}</td>
      <td>{{ trade.currencyTo }}</td>
      <td>{{ trade.amountSell }}</td>
      <td>{{ trade.amountBuy }}</td>
      <td>{{ trade.rate }}</td>
      <td>{{ trade.timePlaced }}</td>
      <td>{{ trade.originatingCountry }}<img :src="trade.countryFlagImageURL"/></td>
    </tr>
  </table>
</template>

<script>
import axios from "axios";

export default {
  name: "Trades",
  data() {
    return {
      trades: [],
      timer: null
    }
  },
  methods: {
    getTrades() {
      axios
          .get("https://localhost:8443/ajax/trade")
          .then(response => (this.trades = this.trades.concat(response.data.trades)))
          .catch(error => console.error(error))
    }
  },
  created() {
    this.timer = setInterval(this.getTrades, 3000); // 3 seconds
  },
  beforeDestroy() {
    clearInterval(this.timer)
  }
}
</script>

<style scoped>
/* refer to http://jsfiddle.net/5WsEt/ */

#trades {
  table-layout: fixed;
  margin: 40px auto 0 auto;
}

#trades th {
  color: #FFFFFF;
}

#trades, td, th {
  border-collapse: collapse;
  border: 1px solid #777;
}

th {
  padding: 20px 7px;
  font-size: 15px;
  color: #444;
  background: #589ab9;
}

td {
  padding: 5px 10px;
  height: 35px;
}

th, td {
  min-width: 200px;
}

#trades img {
  vertical-align: bottom;
}
</style>
