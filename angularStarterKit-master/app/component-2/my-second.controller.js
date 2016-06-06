angular.module('app.component2').controller('MySecondController', function($scope, $http, $modal, games){
   'use strict';

   $scope.data = {
     games : []
   };

   angular.copy(games.data, $scope.data.games);

   $scope.filteredGenre = "";

   $scope.filter = function(value){
     $scope.filteredGenre = value;
   };

   $scope.isFiltered = function (genre){
     return genre == $scope.filteredGenre;
   };

})
