(function () {
    'use strict';

    app.controller('CompanyProfileOutletsController', CompanyProfileOutletsController);

    CompanyProfileOutletsController
        .$inject = [
        '$scope',
        'OutletsFactory'
    ];

    function CompanyProfileOutletsController($scope, OutletsFactory) {
        $scope.outlets = {};
        $scope.currentOutlet = {};

        $scope.edit = edit;
        $scope.save = save;
        $scope.cancel = cancel;

        activate();

        function activate() {
            OutletsFactory.get({},
                function (data) {
                    $scope.outlets = data.outlets;
                }, function (e) {
                    console.error(e);
                });
        }


        function edit(outlet) {
            $scope.currentOutlet = outlet;
        }

        function save(outlet) {
            //OutletsFactory.save({
            //        id: outlet.id
            //    },
            //    function (data) {
            //        console.log(data);
            //    }, function (e) {
            //        console.error(e);
            //    });

            console.log("Saved type with id: " + outlet.id);
        }

        function cancel() {
            $scope.currentOutlet = {};
        }
    }
})();
