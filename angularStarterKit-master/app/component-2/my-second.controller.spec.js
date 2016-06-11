describe('MySecondController tests', function() {
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

	beforeEach(module('app.component2'));

	beforeEach(inject(function($controller, $rootScope){
		$scope = $rootScope.$new();
		$controller('MySecondController', {$scope: $scope, $modal: fakeModal, games: games2});
	}));

	describe('Testing second controller', function() {

		it('should filter game by given value', function() {
			// given
			var value = 'MMORPG';
			// when
			$scope.filter(value);
			// then
      expect($scope.filteredGenre).toBe(value);
		});

		it('should mark genre as filtered', function() {
			// given
			var value = 'Action',
					isFiltered;
			$scope.filteredGenre = 'Action';
			// when
			isFiltered = $scope.isFiltered(value);
			// then
      expect(isFiltered).toBe(true);
		});

		it('should mark genre as filtered', function() {
			// given
			var value = 'FPS',
					isFiltered;
			$scope.filteredGenre = 'Action';
			// when
			isFiltered = $scope.isFiltered(value);
			// then
      expect(isFiltered).toBe(false);
		});

	});
});
