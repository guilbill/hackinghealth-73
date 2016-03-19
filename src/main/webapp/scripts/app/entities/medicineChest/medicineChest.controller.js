'use strict';

angular.module('hackinghealthApp')
    .controller('MedicineChestController', function ($scope, $state, MedicineChest) {

        $scope.medicineChests = [];
        $scope.loadAll = function() {
            MedicineChest.query(function(result) {
               $scope.medicineChests = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.medicineChest = {
                open: false,
                id: null
            };
        };
    });
