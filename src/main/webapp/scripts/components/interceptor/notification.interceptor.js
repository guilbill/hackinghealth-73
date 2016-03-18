 'use strict';

angular.module('hackinghealthApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-hackinghealthApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-hackinghealthApp-params')});
                }
                return response;
            }
        };
    });
