'use strict';

angular.module('hackinghealthApp').controller('MedicineChestDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MedicineChest',
        function($scope, $stateParams, $uibModalInstance, entity, MedicineChest) {

        $scope.medicineChest = entity;
        $scope.load = function(id) {
            MedicineChest.get({id : id}, function(result) {
                $scope.medicineChest = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hackinghealthApp:medicineChestUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.medicineChest.id != null) {
                MedicineChest.update($scope.medicineChest, onSaveSuccess, onSaveError);
            } else {
                MedicineChest.save($scope.medicineChest, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
