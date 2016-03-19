'use strict';

describe('Controller Tests', function() {

    describe('MedicineChestLog Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMedicineChestLog, MockMedicineChest;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMedicineChestLog = jasmine.createSpy('MockMedicineChestLog');
            MockMedicineChest = jasmine.createSpy('MockMedicineChest');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MedicineChestLog': MockMedicineChestLog,
                'MedicineChest': MockMedicineChest
            };
            createController = function() {
                $injector.get('$controller')("MedicineChestLogDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'hackinghealthApp:medicineChestLogUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
