<template>
  <h1 id="header">Calculator</h1>
  <div class="calculator">
    <div id="display" class="display">{{ current || '0' }}</div>
    <div @click="clear"       class="btn operator-top">C</div>
    <div @click="sign"        class="btn operator-top">+/-</div>
    <div @click="percent"     class="btn operator-top">%</div>
    <div @click="divide"      class="btn operator-side">÷</div>
    <div @click="append('7')" class="btn">7</div>
    <div @click="append('8')" class="btn">8</div>
    <div @click="append('9')" class="btn">9</div>
    <div @click="multiply"    class="btn operator-side">x</div>
    <div @click="append('5')" class="btn">5</div>
    <div @click="append('6')" class="btn">6</div>
    <div @click="append('4')" class="btn">4</div> 
    <div @click="subtract"    class="btn operator-side">-</div>
    <div @click="append('1')" class="btn">1</div>
    <div @click="append('2')" class="btn">2</div>
    <div @click="append('3')" class="btn">3</div>
    <div @click="add"         class = "btn operator-side">+</div>
    <div @click="append('0')" class="btn zero">0</div>
    <div @click="dot"         class="btn">.</div>
    <div @click="equal"       class="btn operator-side">=</div>
    <div class="calculations">
      <div v-for="(calculation, index) in calculations" :key="index">
        {{ calculation }} </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const apiUrl = 'http://localhost:8080/solve';

const previous = ref(null);
const current = ref('');
const operator = ref(null);
const operatorClicked = ref(false);
const calculations = ref([]);


const calculate = async (num1, operator, num2) => {
  try {
    const response = await axios.post(apiUrl, {
      expression: current.value, 
      num1, 
      operator: operator === 'x' ? '*' : operator === '÷' ? '/' : operator,
      num2
    });
    console.log('Calculation response', response.data);
    return response.data;
  } catch (error) {
    console.error('Calculation error', error);
    return null;  
  }
}

const equal = async () => {
  if (!operator.value || !previous.value) return;
  const equation = await calculate(previous.value, operator.value, current.value);

  if (!equation || equation.answer === 'Cant divide by zero') {
    current.value = 'Cant divide by zero';
    previous.value = null;
    return;
  } else {
    const calculation = `${previous.value} ${operator.value} ${current.value} = ${equation.answer}`;
    current.value = `${equation.answer}`;
    previous.value = null;
    calculations.value.push(calculation);
  }
}

const clear = () => {
    current.value = '';
};
const sign = () => {
current.value = current.value.charAt(0) === '-' ?
  current.value.slice(1) : `-${current.value}`;
};

const percent = () => {
  const percentage = parseFloat(current.value) / 100;
  current.value = `${percentage}`;
};

const append = (number) => {
  if (operatorClicked.value) {
    current.value = '';
    operatorClicked.value = false;
  }
  current.value = `${current.value}${number}`;
};

const dot = () => {
  if (current.value.indexOf('.') === -1) {
    append('.');
  }
};

const setPrevious = () => {
  previous.value = current.value;
  operatorClicked.value = true;
};

const divide = () => {
  if (current.value === '0') return;
  operator.value = '÷';
  setPrevious();
};

const multiply = () => {
  operator.value = 'x';
  setPrevious();
  console.log(operator.value)
};

const subtract = () => {
  operator.value = '-';
  setPrevious();
};

const add = () => {
  operator.value = '+';
  setPrevious();
};

</script>

<style scoped>
.calculator {
  padding-top: 5%;
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
  border: 0.5px solid white;
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
  background-color: #faffbf;
  border-radius: 15px;
  border: 1px solid #ffff8b;
  cursor: pointer;
  padding: 20px;  
  color: black;
  text-align: center;
  font-weight: bold;
}
.btn:hover {
  background-color: #ffff8b;
}

.operator-side {
  background-color: #FEC868;
  border: 1px solid #fdb231;
}

.operator-side:hover {
  background-color: #fdb231;
}

.operator-top {
  background-color: #BDB2FF;
  border: 1px solid #a292fe;
}

.operator-top:hover {
  background-color: #a292fe;
}
.calculations {
  grid-column: 1 / 5;
  padding: 20px;
  border: 1px solid #FAD1FA;
  background-color: white;
  text-align: center;
  color: #9f825a;
  font-weight: bold;
  border-radius: 15px;
}

#header {
  text-align: center;
  color: #9f825a;
  font-size: 50px;
  margin-bottom: 5px;
}

</style>