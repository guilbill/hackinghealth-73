'use strict';

describe('Controller Tests', function() {

    describe('MedicineChest Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMedicineChest;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMedicineChest = jasmine.createSpy('MockMedicineChest');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MedicineChest': MockMedicineChest
            };
            createController = function() {
                $injector.get('$controller')("MedicineChestDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'hackinghealthApp:medicineChestUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
