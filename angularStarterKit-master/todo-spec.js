describe('TODO list', function() {

  it('should add new game', function() {

     //browser.pause();
     browser.get('http://localhost:9000/#/component-1/dialog-a/');

     expect(element.all(by.repeater('game in data.games')).count()).toEqual(10);

     element(by.buttonText('Add game')).click();

     element(by.model('game.title')).sendKeys('test title');
     element(by.model('game.genre')).sendKeys('test genre');

     element(by.buttonText('Add')).click();

     expect(element.all(by.repeater('game in data.games')).count()).toEqual(11);

  });

  it('should edit game title', function() {

     //browser.pause();
     //browser.get('http://localhost:9000/#/component-1/dialog-a/');

     element( by.repeater('game in data.games').row(0)).click();

     element(by.buttonText('Edit game: Overwatch')).click();

     element(by.model('selectedGame.title')).clear();
     element(by.model('selectedGame.title')).sendKeys('test title');

     element(by.buttonText('Save')).click();

     expect(element( by.repeater('game in data.games').row(10).column('title')).getText()).toEqual('test title');

  });

  it('should edit game title', function() {

     //browser.pause();
     browser.get('http://localhost:9000/#/component-2/dialog-b/');

     element( by.repeater('game in data.games').row(0).column('genre')).click();

     //expect(element.all( by.css('[data-ng-show="isFiltered(game.genre)"]') ).count()).toEqual(10);

     //expect(element.all(by.css('[data-ng-show="isFiltered(game.genre)"]')).filter(function(elem, index) {
       //return elem.getCssValue().then(function(text) {
         //return text === true;
       //});
     //}).count()).toEqual(1);

     //expect(element.all(by.repeater('game in data.games')).count()).toEqual(11);

     expect(element.all(by.css('[data-ng-show="isFiltered(game.genre)"]')).get(0).isDisplayed()).toBe(true);

  });

});
