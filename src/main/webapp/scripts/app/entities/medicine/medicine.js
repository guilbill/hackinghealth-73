'use strict';

angular.module('hackinghealthApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('medicine', {
                parent: 'entity',
                url: '/medicines',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hackinghealthApp.medicine.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/medicine/medicines.html',
                        controller: 'MedicineController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('medicine');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('medicine.detail', {
                parent: 'entity',
                url: '/medicine/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hackinghealthApp.medicine.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/medicine/medicine-detail.html',
                        controller: 'MedicineDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('medicine');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Medicine', function($stateParams, Medicine) {
                        return Medicine.get({id : $stateParams.id});
                    }]
                }
            })
            .state('medicine.new', {
                parent: 'medicine',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicine/medicine-dialog.html',
                        controller: 'MedicineDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    designation: null,
                                    cip: null,
                                    vidalId: null,
                                    numberOfPills: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('medicine', null, { reload: true });
                    }, function() {
                        $state.go('medicine');
                    })
                }]
            })
            .state('medicine.edit', {
                parent: 'medicine',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicine/medicine-dialog.html',
                        controller: 'MedicineDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Medicine', function(Medicine) {
                                return Medicine.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('medicine', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('medicine.delete', {
                parent: 'medicine',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medicine/medicine-delete-dialog.html',
                        controller: 'MedicineDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Medicine', function(Medicine) {
                                return Medicine.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('medicine', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
