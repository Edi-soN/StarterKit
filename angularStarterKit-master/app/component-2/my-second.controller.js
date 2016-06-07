angular.module('app.component2').controller('MySecondController', function($scope, $http, $modal, games, myServiceA){
   'use strict';

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

   $scope.filteredGenre = "";

   $scope.filter = function(value){
     $scope.filteredGenre = value;
   };

   $scope.isFiltered = function (genre){
     return genre == $scope.filteredGenre;
   };

})
