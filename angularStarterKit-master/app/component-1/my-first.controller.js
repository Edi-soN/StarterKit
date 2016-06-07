angular.module('app.component1').controller('MyFirstController', function($scope, $http, $modal, games, myServiceA){
   'use strict';

   $scope.set_color = function (index) {
   if (index === $scope.selectedGameRowIndex) {
     return {'background-color':'#e7e7e7'};
    }
   }

   $scope.data = {
     games : []
   };

   if(myServiceA.getValue() === undefined){
     angular.copy(games.data, $scope.data.games);
     myServiceA.setValue($scope.data.games);
   }
   else{
     angular.copy(myServiceA.getValue(), $scope.data.games);
   }

   //angular.copy(games.data, $scope.data.games);

    $scope.selectedGame = "";
    $scope.selectedGameRowIndex = undefined;
    $scope.shouldShowButton = false;

    $scope.selectGame = function(game, index){
      if($scope.selectedGame == game){
        $scope.shouldShowButton = !$scope.shouldShowButton;

        if($scope.selectedGameRowIndex == undefined){
          $scope.selectedGameRowIndex = index;
        }
        else{
          $scope.selectedGameRowIndex = undefined;
        }

      }
      else{
        $scope.shouldShowButton = true;
        $scope.selectedGameRowIndex = index;
      }

      $scope.selectedGame = game;
    };

    $scope.isGameSelected = function(){
      return $scope.shouldShowButton;
    };


   $scope.addGame = function(){
     var modalInstance = $modal.open({
         templateUrl: '/component-1/modal-dialog/modal-dialog-addGame.tpl.html',
         controller: 'NewGameController',
         size: 'lg',
         resolve: {
             newGame: function(){
                 return;
             }
         }
     });

     modalInstance.result.then(function (game) {
       $scope.data.games.push(game);
       myServiceA.setValue($scope.data.games);
     });

   };

   $scope.editGame = function(){
     var modalInstance = $modal.open({
         templateUrl: '/component-1/modal-dialog/modal-dialog-editGame.tpl.html',
         controller: 'EditGameController',
         size: 'lg',
         resolve: {
             selectedGame: function(){
                 return $scope.selectedGame;
             }
         }
     });

     modalInstance.result.then(function (game) {
       $scope.data.games[$scope.selectedGameRowIndex] = game;
       myServiceA.setValue($scope.data.games);
     });

   };

}).controller('EditGameController', function($scope, $modalInstance, selectedGame){
    'use strict';

    $scope.selectedGame = {};

    angular.copy(selectedGame, $scope.selectedGame);

    $scope.ok = function () {
      $modalInstance.close($scope.selectedGame);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };

}).controller('NewGameController', function($scope, $modalInstance, newGame){
    'use strict';

    $scope.game = {
      "title": undefined,
      "year": undefined,
      "genre": undefined,
      "publisher": undefined,
      "platform": undefined,
      "metascore": undefined
    };

    $scope.ok = function () {
      $modalInstance.close($scope.game);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };

}).service("myServiceA", function(){
    'use strict';

    var sharedGames = undefined;

    this.setValue = function(newValue) {
      sharedGames = newValue;
    };

    this.getValue = function() {
        return sharedGames;
    };

});
