<template>
  <div class="calculator">
    <div class="display">{{ current || '0' }}</div>
    <div @click="clear" class="btn operator-top">C</div>
    <div @click="sign" class="btn operator-top">+/-</div>
    <div @click="percent" class="btn operator-top">%</div>
    <div class="btn operator-side">÷</div>
    <div class="btn">7</div>
    <div class="btn">8</div>
    <div class="btn">9</div>
    <div class="btn operator-side">x</div>
    <div class="btn">5</div>
    <div class="btn">6</div>
    <div class="btn">4</div>
    <div class="btn operator-side">-</div>
    <div class="btn">1</div>
    <div class="btn">2</div>
    <div class="btn">3</div>
    <div class="btn operator-side">÷</div>
    <div class="btn zero">0</div>
    <div class="btn">.</div>
    <div class="btn operator-side">=</div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      current: '600',
    }
  },
  methods: {
    clear() {
      this.current = ''
    },
    sign() {
      this.current = this.current.charAt(0) === '-' ? 
        this.current.slice(1) : `-${this.current}`
    }, 
    percent() {
      this.current = `${parseFloat(this.current) / 100}`
    },
  }
}
</script>

<style scoped>
.calculator {
  width: 300px;
  gap: 5px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: minmax(50px, auto);
}
.display {
  grid-column: 1 / 5;
  border-radius: 10px;
  background-color: #DFFDFF ;
  font-size: 30px;
  font-weight: bold;
  text-align: right;
  padding: 15px;
  color: black;
}
.zero {
  grid-column: 1 / 3;
}

.btn {
  background-color: #F1F7B5;
  border-radius: 15px;
  cursor: pointer;
  padding: 20px;  
  color: black;
  text-align: center;
  font-weight: bold;
}

.operator-side {
  background-color: #FEC868;
  border: 0.5px solid #DFFDFF;
}

.operator-top {
  background-color: #BDB2FF;
  border: 0.5px solid #DFFDFF;
}

</style>
