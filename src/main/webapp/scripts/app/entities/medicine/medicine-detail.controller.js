'use strict';

angular.module('hackinghealthApp')
    .controller('MedicineDetailController', function ($scope, $rootScope, $stateParams, entity, Medicine, MedicineChest) {
        $scope.medicine = entity;
        $scope.load = function (id) {
            Medicine.get({id: id}, function(result) {
                $scope.medicine = result;
            });
        };
        var unsubscribe = $rootScope.$on('hackinghealthApp:medicineUpdate', function(event, result) {
            $scope.medicine = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
