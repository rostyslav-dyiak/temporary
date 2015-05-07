(function () {
    'use strict';

    app.controller('PublicHolidayAddEditController', PublicHolidayAddEditController);
    PublicHolidayAddEditController
        .$inject = [
        '$scope',
        '$stateParams',
        'toaster',
        'PublicHolidayFactory'
    ];

    function PublicHolidayAddEditController($scope, $stateParams, toaster, PublicHolidayFactory) {
        $scope.publicHolidayId = $stateParams.id;
        $scope.publicHoliday = {};
        $scope.opened = false;

        $scope.save = save;
        $scope.delete = deleteHoliday;
        $scope.openDatePicker = openDatePicker;

        activate();

        function activate() {
            if ($scope.publicHolidayId) {
                PublicHolidayFactory.get({
                        id: $scope.publicHolidayId
                    },
                    function (data) {
                        $scope.publicHoliday = data;
                    }, function (e) {
                        console.error(e);
                    });
            }
        }

        function save() {
            if ($scope.publicHolidayId) {
                PublicHolidayFactory.update($scope.publicHoliday,
                    function () {
                        toaster.pop('success', 'Success', 'Public Holiday has been updated');
                    }, function (e) {
                        console.error(e);
                        toaster.pop('error', 'Error', 'Please try again');
                    });
            } else {
                PublicHolidayFactory.save($scope.publicHoliday,
                    function () {
                        toaster.pop('success', 'Success', 'Public Holiday has been created');
                    }, function (e) {
                        console.error(e);
                        toaster.pop('error', 'Error', 'Please try again');
                    });
            }
        }

        function deleteHoliday() {
            PublicHolidayFactory.delete({
                    id: $scope.publicHoliday.id
                },
                function () {
                    toaster.pop('success', 'Success', 'Public Holiday has been deleted');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function openDatePicker($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        }
    }

})();
