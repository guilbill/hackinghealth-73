'use strict';

angular.module('hackinghealthApp')
	.controller('MedicineChestLogDeleteController', function($scope, $uibModalInstance, entity, MedicineChestLog) {

        $scope.medicineChestLog = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MedicineChestLog.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
