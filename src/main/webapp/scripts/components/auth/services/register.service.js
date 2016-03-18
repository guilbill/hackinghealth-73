'use strict';

angular.module('hackinghealthApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


