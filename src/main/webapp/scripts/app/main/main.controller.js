'use strict';

angular.module('hackinghealthApp')
    .controller('MainController', function ($scope, $state, $uibModal, Principal, Medicine) {
        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;

        });
        $scope.scanCIP = function () {
            if ($scope.cip.indexOf("3400938358926") > -1) {
                $state.go('perime');
            } else if ($scope.cip.indexOf("3400936381636") > -1){
                $state.go('interaction');
            } else {
                //Medicine.
                $state.go('stockAction',
                    {
                        medicine: {
                            name: "DAFALGAN 500mg CPR",
                            cip: $scope.cip,
                            monographie: 'une monographie'
                        }
                    });
            }
        }
    });
