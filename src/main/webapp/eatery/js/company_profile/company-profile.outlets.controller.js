(function () {
    'use strict';

    app.controller('CompanyProfileOutletsController', CompanyProfileOutletsController);

    CompanyProfileOutletsController
        .$inject = [
        '$scope',
        'OutletsFactory',
        'OutletsCompanyFactory'
    ];

    function CompanyProfileOutletsController($scope, OutletsFactory,OutletsCompanyFactory) {
        $scope.outlets = {};
        $scope.currentOutlet = {};

        $scope.edit = edit;
        $scope.save = save;
        $scope.cancel = cancel;

        activate();

        function activate() {
            OutletsCompanyFactory.query({},
                function (data) {
                    $scope.outlets = data;
                }, function (e) {
                    console.error(e);
                });
        }


        function edit(outlet) {
            $scope.currentOutlet = outlet;
        }

        function save(outlet) {
            OutletsFactory.update($scope.currentOutlet,
                function (data) {
                    console.log(data);
                }, function (e) {
                    console.error(e);
                });

            console.log("Saved type with id: " + outlet.id);
        }

        function cancel() {
            $scope.currentOutlet = {};
        }
    }
})();
