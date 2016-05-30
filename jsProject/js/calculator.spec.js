'use strict';

// addition tests
describe('calculator addition test', function() {
      it('should return sum of two given numbers', function() {
        // given
        var firstNumber = 4,
            secondNumber = 3,
            result;
        // when
        result = calculator.addition(firstNumber, secondNumber);
        // then
        expect(result).toBe(7);
      });
});

describe('calculator addition invalid argument test', function() {
      it('should return zero for invalid argument', function() {
        // given
        var firstNumber = " ",
            secondNumber = "test",
            result;
        // when
        result = calculator.addition(firstNumber, secondNumber);
        // then
        expect(result).toBe(0);
      });
});

// ----------------------------------------------------------------------------
// substraction tests
describe('calculator substraction test', function() {
      it('should return differene of two given numbers', function() {
        // given
        var firstNumber = 4,
            secondNumber = 3,
            result;
        // when
        result = calculator.substraction(firstNumber, secondNumber);
        // then
        expect(result).toBe(1);
      });
});

describe('calculator substraction invalid argument test', function() {
      it('should return zero for invalid argument', function() {
        // given
        var firstNumber = " ",
            secondNumber = "test",
            result;
        // when
        result = calculator.substraction(firstNumber, secondNumber);
        // then
        expect(result).toBe(0);
      });
});

// ----------------------------------------------------------------------------
// multiplication tests
describe('calculator multiplication test', function() {
      it('should return product of two given numbers', function() {
        // given
        var firstNumber = 4,
            secondNumber = 3,
            result;
        // when
        result = calculator.multiplication(firstNumber, secondNumber);
        // then
        expect(result).toBe(12);
      });
});

describe('calculator multiplication invalid argument test', function() {
      it('should return zero for invalid argument', function() {
        // given
        var firstNumber = " ",
            secondNumber = "test",
            result;
        // when
        result = calculator.multiplication(firstNumber, secondNumber);
        // then
        expect(result).toBe(0);
      });
});

// ----------------------------------------------------------------------------
// division tests
describe('calculator division test', function() {
      it('should return quotient of two given numbers', function() {
        // given
        var firstNumber = 12,
            secondNumber = 3,
            result;
        // when
        result = calculator.division(firstNumber, secondNumber);
        // then
        expect(result).toBe(4);
      });
});

describe('calculator division invalid argument test', function() {
      it('should return zero for invalid argument', function() {
        // given
        var firstNumber = " ",
            secondNumber = "test",
            result;
        // when
        result = calculator.division(firstNumber, secondNumber);
        // then
        expect(result).toBe(0);
      });
});

// ----------------------------------------------------------------------------
// factorial tests
describe('calculator factorial test', function() {
      it('should return factorial of given number', function() {
        // given
        var firstNumber = 3,
            result;
        // when
        result = calculator.factorial(firstNumber);
        // then
        expect(result).toBe(6);
      });
});

describe('calculator factorial invalid argument test', function() {
      it('should return zero for invalid argument', function() {
        // given
        var firstNumber = "test",
            result;
        // when
        result = calculator.factorial(firstNumber);
        // then
        expect(result).toBe(0);
      });
});

describe('calculator factorial test', function() {
      it('should return factorial of given number', function() {
        // given
        var firstNumber = 0,
            result;
        // when
        result = calculator.factorial(firstNumber);
        // then
        expect(result).toBe(1);
      });
});

describe('calculator factorial test', function() {
      it('should return factorial of given number', function() {
        // given
        var firstNumber = 0,
            result;
        // when
        result = calculator.factorial(firstNumber);
        // then
        expect(result).toBe(1);
      });
});
