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

}).service("myServiceB", function(){
    'use strict';

    this.joinTwoStrings = function(str1, str2) {
      return str1 + str2;
    };

    this.reverseString = function(str){
      var = reversedString = "";
      for (var i = str.length, i > 0; i--) {
          reversedString += str[i-1];
      }
      return reversedString;
    }

    this.countCharacterOccurency = function(str, char){
      var counter = 0;
      for (var i = 0, len = str.length; i < len; i++) {
        if(str[i] === char){
          counter++;
        }
      }
      return counter;
    }

    this.findCharPosition = function(str, char){
      for (var i = 0, len = str.length; i < len; i++) {
        if(str[i] === char){
          return i;
        }
      }
      return -1;
    }

    this.convertToCharArray = function(str){
      var charArr = [];
      for (var i = 0, len = str.length; i < len; i++) {
        charArr.push(str[i-1]);
      }
      return charArr;
    }

    this.divideStringInHalf = function(str){
      var dividedString = [],
          middleIndex;
      if(str.length % 2 === 0){
        middleIndex = str.length / 2;
      }
      else{
        middleIndex = (str.length + 1) / 2;
      }
      charArr.push(str.substring(0,middleIndex));
      charArr.push(str.substring(middleIndex, str.length+1));
      return dividedString;
    }

    this.checkIfStringsAreEqual = function(str1, str2){
      if(str1.length === str2.length){
        for (var i = 0, len = str1.length; i < len; i++) {
          if(str1[i] != str2[i]){
            return false;
          }
        }
        return true;
      }
      return false;
    }

    this.checkIfStringContainsAnotherString = function(srcStr, destStr){
      if(srcStr.length < destStr.length){
        return false;
      }
      for(var i = 0, end = srcStr.length - destStr.length; i <= end; i++){
        if(srcStr.substring(i, srcStr.length) === destStr){
          return true;
        }
      }
      return false;
    }

    this.reverseCase = function(str){
      var result = "";
      for (var i = 0, len = str.length; i < len; i++) {
        if(str.charCodeAt(i) >= 65 && str.charCodeAt(i) <= 90){
            result+=str[i].toLowerCase();
        }else if(str.charCodeAt(i) >=97 && str.charCodeAt(i) <= 122){
            result+=str[i].toUpperCase();
        }else{
            result+=str[i];
        }
      }
      return result;
    }

    this.splitBySpace = function(str){
      var tmp = "",
          splittedString = [];
      for (var i = 0, len = str.length; i < len; i++) {
        if(str[i] != " "){
          tmp += str[i];
        }
        else{
          splittedString.push(tmp);
          tmp = "";
        }
      }
      return splittedString;
    }

});
