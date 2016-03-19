'use strict';

angular.module('hackinghealthApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('medicineChestLog', {
                parent: 'entity',
                url: '/medicineChestLogs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hackinghealthApp.medicineChestLog.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/medicineChestLog/medicineChestLogs.html',
                        controller: 'MedicineChestLogController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('medicineChestLog');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('medicineChestLog.detail', {
                parent: 'entity',
                url: '/medicineChestLog/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hackinghealthApp.medicineChestLog.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/medicineChestLog/medicineChestLog-detail.html',
                        controller: 'MedicineChestLogDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('medicineChestLog');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'MedicineChestLog', function($stateParams, MedicineChestLog) {
                        return MedicineChestLog.get({id : $stateParams.id});
                    }]
                }
            })
            .state('medicineChestLog.new', {
                parent: 'medicineChestLog',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicineChestLog/medicineChestLog-dialog.html',
                        controller: 'MedicineChestLogDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    date: null,
                                    open: false,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('medicineChestLog', null, { reload: true });
                    }, function() {
                        $state.go('medicineChestLog');
                    })
                }]
            })
            .state('medicineChestLog.edit', {
                parent: 'medicineChestLog',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicineChestLog/medicineChestLog-dialog.html',
                        controller: 'MedicineChestLogDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MedicineChestLog', function(MedicineChestLog) {
                                return MedicineChestLog.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('medicineChestLog', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('medicineChestLog.delete', {
                parent: 'medicineChestLog',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicineChestLog/medicineChestLog-delete-dialog.html',
                        controller: 'MedicineChestLogDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MedicineChestLog', function(MedicineChestLog) {
                                return MedicineChestLog.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('medicineChestLog', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
