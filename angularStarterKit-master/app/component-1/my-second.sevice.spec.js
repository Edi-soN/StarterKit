describe('string service tests', function () {
	  'use strict';

	  var myServiceB;

	  beforeEach(module('app.component1'));

	  beforeEach(inject(function (_myServiceB_) {
		myServiceB = _myServiceB_;
	  }));

	  describe('testing string functions', function () {

      it('should return merged string', function () {
			  // given
			  var str1 = "First",
						str2 = "Second",
						result = "";
			  // when
         result = myServiceB.joinTwoStrings(str1, str2);
			  // then
			  expect(result).toEqual("FirstSecond");
		  });

	  });
});
