'use strict';

angular.module('hackinghealthApp').controller('MedicationDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Medication',
        function($scope, $stateParams, $uibModalInstance, entity, Medication) {

        $scope.medication = entity;
        $scope.load = function(id) {
            Medication.get({id : id}, function(result) {
                $scope.medication = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hackinghealthApp:medicationUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.medication.id != null) {
                Medication.update($scope.medication, onSaveSuccess, onSaveError);
            } else {
                Medication.save($scope.medication, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
