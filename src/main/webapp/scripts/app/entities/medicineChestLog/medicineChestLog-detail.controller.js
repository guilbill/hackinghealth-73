'use strict';

angular.module('hackinghealthApp')
    .controller('MedicineChestLogDetailController', function ($scope, $rootScope, $stateParams, entity, MedicineChestLog, MedicineChest) {
        $scope.medicineChestLog = entity;
        $scope.load = function (id) {
            MedicineChestLog.get({id: id}, function(result) {
                $scope.medicineChestLog = result;
            });
        };
        var unsubscribe = $rootScope.$on('hackinghealthApp:medicineChestLogUpdate', function(event, result) {
            $scope.medicineChestLog = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
