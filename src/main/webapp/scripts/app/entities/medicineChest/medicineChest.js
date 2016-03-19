'use strict';

angular.module('hackinghealthApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('medicineChest', {
                parent: 'entity',
                url: '/medicineChests',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hackinghealthApp.medicineChest.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/medicineChest/medicineChests.html',
                        controller: 'MedicineChestController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('medicineChest');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('medicineChest.detail', {
                parent: 'entity',
                url: '/medicineChest/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hackinghealthApp.medicineChest.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/medicineChest/medicineChest-detail.html',
                        controller: 'MedicineChestDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('medicineChest');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'MedicineChest', function($stateParams, MedicineChest) {
                        return MedicineChest.get({id : $stateParams.id});
                    }]
                }
            })
            .state('medicineChest.new', {
                parent: 'medicineChest',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicineChest/medicineChest-dialog.html',
                        controller: 'MedicineChestDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    open: false,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('medicineChest', null, { reload: true });
                    }, function() {
                        $state.go('medicineChest');
                    })
                }]
            })
            .state('medicineChest.edit', {
                parent: 'medicineChest',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicineChest/medicineChest-dialog.html',
                        controller: 'MedicineChestDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MedicineChest', function(MedicineChest) {
                                return MedicineChest.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('medicineChest', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('medicineChest.delete', {
                parent: 'medicineChest',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicineChest/medicineChest-delete-dialog.html',
                        controller: 'MedicineChestDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MedicineChest', function(MedicineChest) {
                                return MedicineChest.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('medicineChest', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
