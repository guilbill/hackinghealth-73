'use strict';

angular.module('hackinghealthApp')
    .controller('MedicationDetailController', function ($scope, $rootScope, $stateParams, entity, Medication) {
        $scope.medication = entity;
        $scope.load = function (id) {
            Medication.get({id: id}, function(result) {
                $scope.medication = result;
            });
        };
        var unsubscribe = $rootScope.$on('hackinghealthApp:medicationUpdate', function(event, result) {
            $scope.medication = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
