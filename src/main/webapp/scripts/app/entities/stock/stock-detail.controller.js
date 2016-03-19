'use strict';

angular.module('hackinghealthApp')
    .controller('StockDetailController', function ($scope, $rootScope, $stateParams, entity, Stock, Medicine) {
        $scope.stock = entity;
        $scope.load = function (id) {
            Stock.get({id: id}, function(result) {
                $scope.stock = result;
            });
        };
        var unsubscribe = $rootScope.$on('hackinghealthApp:stockUpdate', function(event, result) {
            $scope.stock = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
