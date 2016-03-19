'use strict';

angular.module('hackinghealthApp')
    .controller('MedicationController', function ($scope, $state, Medication) {

        $scope.medications = [];
        $scope.loadAll = function() {
            Medication.query(function(result) {
               $scope.medications = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.medication = {
                cip: null,
                name: null,
                lotNumber: null,
                minStock: null,
                id: null
            };
        };
    });
