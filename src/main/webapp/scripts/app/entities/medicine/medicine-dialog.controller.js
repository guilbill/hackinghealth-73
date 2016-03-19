'use strict';

angular.module('hackinghealthApp').controller('MedicineDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Medicine', 'MedicineChest',
        function($scope, $stateParams, $uibModalInstance, entity, Medicine, MedicineChest) {

        $scope.medicine = entity;
        $scope.medicinechests = MedicineChest.query();
        $scope.load = function(id) {
            Medicine.get({id : id}, function(result) {
                $scope.medicine = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hackinghealthApp:medicineUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.medicine.id != null) {
                Medicine.update($scope.medicine, onSaveSuccess, onSaveError);
            } else {
                Medicine.save($scope.medicine, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
