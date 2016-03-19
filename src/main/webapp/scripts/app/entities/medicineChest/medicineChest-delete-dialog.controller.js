'use strict';

angular.module('hackinghealthApp')
	.controller('MedicineChestDeleteController', function($scope, $uibModalInstance, entity, MedicineChest) {

        $scope.medicineChest = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MedicineChest.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
