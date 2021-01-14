<template>
  <div>
    <p>{{trade.id}}</p>
  </div>
</template>

<script>
export default {
  name: 'Trade',
  props: {
    trade : Object
  }
}
</script>
