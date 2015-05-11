(function () {
    'use strict';

    app.controller('CompanyProfileOutletsController', CompanyProfileOutletsController);

    CompanyProfileOutletsController
        .$inject = [
        '$scope',
        'toaster',
        'OutletsFactory',
        'OutletsCompanyFactory'
    ];

    function CompanyProfileOutletsController($scope, toaster, OutletsFactory,OutletsCompanyFactory) {
        $scope.outlets = {};
        $scope.currentOutlet = {};

        $scope.edit = edit;
        $scope.save = save;

        activate();

        function activate() {
            OutletsCompanyFactory.query({},
                function (data) {
                    $scope.outlets = data;
                }, function (e) {
                    toaster.pop('error', 'Error', 'Error in initialization');
                });
        }


        function edit(outlet) {
            $scope.currentOutlet = outlet;
        }

        function save(outlet) {
            OutletsFactory.update($scope.currentOutlet,
                function (data) {
                    console.log(data);
                    toaster.pop('success', 'Success', 'Outlet saved');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });

            console.log("Saved type with id: " + outlet.id);
        }
    }
})();
