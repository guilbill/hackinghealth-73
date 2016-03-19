'use strict';

angular.module('hackinghealthApp')
    .controller('MedicineChestDetailController', function ($scope, $rootScope, $stateParams, entity, MedicineChest) {
        $scope.medicineChest = entity;
        $scope.load = function (id) {
            MedicineChest.get({id: id}, function(result) {
                $scope.medicineChest = result;
            });
        };
        var unsubscribe = $rootScope.$on('hackinghealthApp:medicineChestUpdate', function(event, result) {
            $scope.medicineChest = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
