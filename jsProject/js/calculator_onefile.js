'use strict';

var calculator = (function(){
  var a = document.getElementById('firstNumber'),
      b = document.getElementById('secondNumber'),
      result = document.getElementById('result');
  //if( isNaN(parseInt(a.value)) || isNaN(parseInt(b.value)) ){
  if( !(a.value && b.value) ){
    a.value = 0;
    b.value = 0;
  }
  return {
    addition : function(){
      result.value = parseInt(a.value) + parseInt(b.value);
      //var a = parseInt(document.getElementById('firstNumber').value),
          //b = parseInt(document.getElementById('secondNumber').value);

          //document.getElementById('result').value = (a + b);
    },
    substraction : function(){
      result.value = parseInt(a.value) - parseInt(b.value);
    },
    multiplication : function(){
      result.value = parseInt(a.value) * parseInt(b.value);
    },
    division : function(){
      if( parseInt(b.value) === 0 ){
        result.value = 0;
      }
      else{
        result.value = parseInt(a.value) / parseInt(b.value);
      }
    },
    factorial : function(){
      var tmp = 1;
      if( parseInt(a.value) <= 1){
        result.value = 1;
      }
      else{
        for( var counter = parseInt(a.value); counter > 1; counter-=1){
          tmp = tmp * counter;
        }
          result.value = tmp;
      }
    }
  }
})();
