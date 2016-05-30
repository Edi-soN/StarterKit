'use strict';

var calculator_interface = (function(){
  // set initial state
  document.getElementById('firstNumber').value = 0;
  document.getElementById('secondNumber').value = 0;
  return {
    addition : function(){
      document.getElementById('result').value = calculator.addition(parseInt(document.getElementById('firstNumber').value), parseInt(document.getElementById('secondNumber').value));
    },
    substraction : function(){
      document.getElementById('result').value = calculator.substraction(parseInt(document.getElementById('firstNumber').value), parseInt(document.getElementById('secondNumber').value));
    },
    multiplication : function(){
      document.getElementById('result').value = calculator.multiplication(parseInt(document.getElementById('firstNumber').value), parseInt(document.getElementById('secondNumber').value));
    },
    division : function(){
      document.getElementById('result').value = calculator.division(parseInt(document.getElementById('firstNumber').value), parseInt(document.getElementById('secondNumber').value));
    },
    factorial : function(){
      document.getElementById('result').value = calculator.factorial(parseInt(document.getElementById('firstNumber').value));
    }
  }
})();
