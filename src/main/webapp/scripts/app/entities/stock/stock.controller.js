'use strict';

angular.module('hackinghealthApp')
    .controller('StockController', function ($scope, $state, Stock) {

        $scope.stocks = [];
        $scope.loadAll = function() {
            Stock.query(function(result) {
               $scope.stocks = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.stock = {
                batchNumber: null,
                numberOfPills: null,
                expirationDate: null,
                id: null
            };
        };
        
        $scope.inStock = function() {
            $state.go("inStock");
        }

        $scope.goHome = function () {
            $state.go("home");
        }
    });
