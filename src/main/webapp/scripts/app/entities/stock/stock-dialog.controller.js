'use strict';

angular.module('hackinghealthApp').controller('StockDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Stock', 'Medicine',
        function($scope, $stateParams, $uibModalInstance, entity, Stock, Medicine) {

        $scope.stock = entity;
        $scope.medicines = Medicine.query();
        $scope.load = function(id) {
            Stock.get({id : id}, function(result) {
                $scope.stock = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hackinghealthApp:stockUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.stock.id != null) {
                Stock.update($scope.stock, onSaveSuccess, onSaveError);
            } else {
                Stock.save($scope.stock, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForExpirationDate = {};

        $scope.datePickerForExpirationDate.status = {
            opened: false
        };

        $scope.datePickerForExpirationDateOpen = function($event) {
            $scope.datePickerForExpirationDate.status.opened = true;
        };
}]);
