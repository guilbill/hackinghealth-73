'use strict';

angular.module('hackinghealthApp')
    .controller('MedicineController', function ($scope, $state, Medicine) {

        $scope.medicines = [];
        $scope.loadAll = function() {
            Medicine.query(function(result) {
               $scope.medicines = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.medicine = {
                designation: null,
                cip: null,
                vidalId: null,
                numberOfPills: null,
                id: null
            };
        };
    });
