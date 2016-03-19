'use strict';

angular.module('hackinghealthApp')
	.controller('MedicineDeleteController', function($scope, $uibModalInstance, entity, Medicine) {

        $scope.medicine = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Medicine.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
