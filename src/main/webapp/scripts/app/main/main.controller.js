'use strict';

angular.module('hackinghealthApp')
    .controller('MainController', function ($scope, $state, $uibModal, Principal, Medicine) {
        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;

        });
        $scope.scanCIP = function () {
            if ($scope.cip.indexOf("01034009383589261718110010185") > -1) {
                alert("Périmé");
            } else if ($scope.cip.indexOf("01034009363816361717050010C264") > -1){
                alert("Interaction!");
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
