'use strict';

angular.module('hackinghealthApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('medication', {
                parent: 'entity',
                url: '/medications',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hackinghealthApp.medication.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/medication/medications.html',
                        controller: 'MedicationController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('medication');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('medication.detail', {
                parent: 'entity',
                url: '/medication/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hackinghealthApp.medication.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/medication/medication-detail.html',
                        controller: 'MedicationDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('medication');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Medication', function($stateParams, Medication) {
                        return Medication.get({id : $stateParams.id});
                    }]
                }
            })
            .state('medication.new', {
                parent: 'medication',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medication/medication-dialog.html',
                        controller: 'MedicationDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    cip: null,
                                    name: null,
                                    lotNumber: null,
                                    minStock: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('medication', null, { reload: true });
                    }, function() {
                        $state.go('medication');
                    })
                }]
            })
            .state('medication.edit', {
                parent: 'medication',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medication/medication-dialog.html',
                        controller: 'MedicationDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Medication', function(Medication) {
                                return Medication.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('medication', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('medication.delete', {
                parent: 'medication',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/medication/medication-delete-dialog.html',
                        controller: 'MedicationDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Medication', function(Medication) {
                                return Medication.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('medication', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
