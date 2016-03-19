'use strict';

angular.module('hackinghealthApp')
    .factory('Medicine', function ($resource, DateUtils) {
        return $resource('api/medicines/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
