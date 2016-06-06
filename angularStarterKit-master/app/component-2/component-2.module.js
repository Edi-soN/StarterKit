angular.module('app.component2', ['ngRoute', 'app.component2.templates'])
    .config(function ($routeProvider) {
        'use strict';
        $routeProvider.when('/component-2/dialog-b', {
          templateUrl: 'component-2/dialog-b/dialog-b.html',
          controller: 'MySecondController',
          resolve: {
              games: function($http){
                  return $http.get('/component-2/games.json');
              }
          }
        });
    });
