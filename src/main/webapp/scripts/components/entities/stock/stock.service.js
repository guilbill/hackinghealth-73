'use strict';

angular.module('hackinghealthApp')
    .factory('Stock', function ($resource, DateUtils) {
        return $resource('api/stocks/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.expirationDate = DateUtils.convertLocaleDateFromServer(data.expirationDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.expirationDate = DateUtils.convertLocaleDateToServer(data.expirationDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.expirationDate = DateUtils.convertLocaleDateToServer(data.expirationDate);
                    return angular.toJson(data);
                }
            }
        });
    });
