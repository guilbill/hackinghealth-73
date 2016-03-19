'use strict';

angular.module('hackinghealthApp')
    .controller('MainController', function ($scope, $state, Principal, Medicine) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            
        });
        $scope.scanCIP = function(){
            //Medicine.
            $state.go('stockAction',
                {
                    medicine: {
                        name:"DAFALGAN 500mg CPR",
                        cip:$scope.cip,
                        monographie:'une monographie'
                    }
                });
        }
    });
