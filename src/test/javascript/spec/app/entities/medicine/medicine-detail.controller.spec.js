'use strict';

describe('Controller Tests', function() {

    describe('Medicine Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMedicine, MockMedicineChest;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMedicine = jasmine.createSpy('MockMedicine');
            MockMedicineChest = jasmine.createSpy('MockMedicineChest');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Medicine': MockMedicine,
                'MedicineChest': MockMedicineChest
            };
            createController = function() {
                $injector.get('$controller')("MedicineDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'hackinghealthApp:medicineUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
