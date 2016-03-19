'use strict';

angular.module('hackinghealthApp').controller('MedicineChestLogDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MedicineChestLog', 'MedicineChest',
        function($scope, $stateParams, $uibModalInstance, entity, MedicineChestLog, MedicineChest) {

        $scope.medicineChestLog = entity;
        $scope.medicinechests = MedicineChest.query();
        $scope.load = function(id) {
            MedicineChestLog.get({id : id}, function(result) {
                $scope.medicineChestLog = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hackinghealthApp:medicineChestLogUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.medicineChestLog.id != null) {
                MedicineChestLog.update($scope.medicineChestLog, onSaveSuccess, onSaveError);
            } else {
                MedicineChestLog.save($scope.medicineChestLog, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForDate = {};

        $scope.datePickerForDate.status = {
            opened: false
        };

        $scope.datePickerForDateOpen = function($event) {
            $scope.datePickerForDate.status.opened = true;
        };
}]);
