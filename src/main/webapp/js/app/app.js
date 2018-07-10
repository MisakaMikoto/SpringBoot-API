/**
 * Created by MisakaMikoto on 2017-08-23.
 */
var app = angular.module('app', ['ngRoute', 'ngCookies', 'ui.bootstrap']).
    config(function($routeProvider, $locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });

        $routeProvider
            .when('/', {templateUrl: '/component/login.html'})
            .when('/signin', {templateUrl: '/component/signin.html'})
            .when('/book', {templateUrl: '/component/book.html'})
            .when('/history', {templateUrl: '/component/searchHistory.html'})
    });