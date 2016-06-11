angular.module('app.component1', ['ngRoute','app.component1.templates'])
    .config(function ($routeProvider) {
        'use strict';
        $routeProvider.when('/component-1/dialog-a', {
            templateUrl: 'component-1/dialog-a/dialog-a.html',
            controller: 'MyFirstController',
            resolve: {
                games: function($http){
                    return $http.get('/component-1/games.json');
                }
            }
        });
    });