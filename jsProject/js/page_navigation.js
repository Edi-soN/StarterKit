'use strict';

// this is an opposite solution to one presented in starter_kit_js_app-solutions
// all logic in one file - is it a good approach?

var navi = (function (){
  var getCalculatorSection = document.getElementById('calc'),
      getHttpRequestSection = document.getElementById('req');

  return {
    showCalc: function (){
      getCalculatorSection.style.display = 'block';
      getHttpRequestSection.style.display = 'none';
    },
    showReq: function(){
      getCalculatorSection.style.display = 'none';
      getHttpRequestSection.style.display = 'block';
    }
  }
})();
