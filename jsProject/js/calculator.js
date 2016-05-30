'use strict';

var calculator = (function(){
  return {
    addition : function(firstNumber, secondNumber){
      if( isNaN(firstNumber) || isNaN(secondNumber) ){
        return 0;
      }
      return firstNumber + secondNumber;
    },
    substraction : function(firstNumber, secondNumber){
      if( isNaN(firstNumber) || isNaN(secondNumber) ){
        return 0;
      }
      return firstNumber - secondNumber;
    },
    multiplication : function(firstNumber, secondNumber){
      if( isNaN(firstNumber) || isNaN(secondNumber) ){
        return 0;
      }
      return firstNumber * secondNumber;
    },
    division : function(firstNumber, secondNumber){
      if( isNaN(firstNumber) || isNaN(secondNumber) || secondNumber === 0 ){
        return 0;
      }
      return firstNumber / secondNumber;
    },
    factorial : function(firstNumber){
      if( isNaN(firstNumber) ){
        return 0;
      }
      var tmp = 1;
      if( firstNumber <= 1){
        return 1;
      }
      else{
        for( var counter = firstNumber; counter > 1; counter-=1){
          tmp = tmp * counter;
        }
          return tmp;
      }
    }
  }
})();
