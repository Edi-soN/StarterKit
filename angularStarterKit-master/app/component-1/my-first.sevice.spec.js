describe('data service tests', function () {
	  'use strict';

	  var myServiceA;

	  beforeEach(module('app.component1'));

	  beforeEach(inject(function (_myServiceA_) {
		myServiceA = _myServiceA_;
	  }));

	  describe('get value method', function () {

      var sharedGames = [
        {
            "title": "Overwatch",
            "year": 2016,
            "genre": "General",
            "publisher": "Blizzard Entertainemnt",
            "platform": "PC",
            "metascore": 96
        },
        {
            "title": "Dark Souls III",
            "year": 2016,
            "genre": "Role-Playing",
            "publisher": "Bandai Namco Games",
            "platform": "PC",
            "metascore": 90
        },
        {
            "title": "DOOM",
            "year": 2016,
            "genre": "Sci-Fi",
            "publisher": "Bethesda Softworks",
            "platform": "PC",
            "metascore": 85
        }
      ];

      it('should return data', function () {
			  // given
			  var data = [];
			  // when
        myServiceA.setValue(sharedGames);
        data = myServiceA.getValue();
			  // then
			  expect(data.length).toEqual(3);
		  });

	  });
});
