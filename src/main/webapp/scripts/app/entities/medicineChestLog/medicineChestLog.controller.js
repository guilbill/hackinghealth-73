'use strict';

angular.module('hackinghealthApp')
    .controller('MedicineChestLogController', function ($scope, $state, MedicineChestLog) {

        $scope.medicineChestLogs = [];
        $scope.loadAll = function() {
            MedicineChestLog.query(function(result) {
               $scope.medicineChestLogs = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.medicineChestLog = {
                date: null,
                open: false,
                id: null
            };
        };
    });
