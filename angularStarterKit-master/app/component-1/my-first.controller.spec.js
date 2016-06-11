describe('MyFirstController tests', function() {
	'use strict';

	var games2 = [],
	    $scope,
	    fakeModal = {
    result: {
        then: function (confirmCallback, cancelCallback) {
            this.confirmCallBack = confirmCallback;
            this.cancelCallback = cancelCallback;
            return this;
        },
        catch: function (cancelCallback) {
            this.cancelCallback = cancelCallback;
            return this;
        },
        finally: function (finallyCallback) {
            this.finallyCallback = finallyCallback;
            return this;
        }
    },
    close: function (item) {
        this.result.confirmCallBack(item);
    },
    dismiss: function (item) {
        this.result.cancelCallback(item);
    },
    finally: function () {
        this.result.finallyCallback();
    }
};

	beforeEach(module('app.component1'));

	beforeEach(inject(function($controller, $rootScope){
		$scope = $rootScope.$new();
		$controller('MyFirstController', {$scope: $scope, $modal: fakeModal, games: games2});
	}));

	describe('Testing first controller', function() {

		var index = 0,
				game = {
					"title": "Overwatch",
					"year": 2016,
					"genre": "General",
					"publisher": "Blizzard Entertainemnt",
					"platform": "PC",
					"metascore": 96
				};

		it('should select game row', function() {
			// given

			// when
			$scope.selectGame(game,index);
			// then
      expect($scope.selectedGameRowIndex).toBe(0);
		});

		it('should select game object', function(){
			// given

			// when
			$scope.selectGame(game,index);
			// then
			expect($scope.selectedGame).toBe(game);
		});

		it('should show button', function(){
			// given

			// when
			$scope.selectGame(game,index);
			// then
			expect($scope.shouldShowButton).toBe(true);
		});

		it('should return background color', function(){
			// given
			$scope.selectedGameRowIndex = 0;
			// when
			var bgColor = {'background-color':'#e7e7e7'},
			    result =  $scope.set_color(index);
			// then
			expect(result).toEqual(bgColor);
		});

	});
});
