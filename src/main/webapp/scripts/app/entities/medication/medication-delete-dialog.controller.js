'use strict';

angular.module('hackinghealthApp')
	.controller('MedicationDeleteController', function($scope, $uibModalInstance, entity, Medication) {

        $scope.medication = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Medication.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
