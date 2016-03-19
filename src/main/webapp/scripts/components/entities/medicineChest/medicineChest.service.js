'use strict';

angular.module('hackinghealthApp')
    .factory('MedicineChest', function ($resource, DateUtils) {
        return $resource('api/medicineChests/:id', {}, {
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
