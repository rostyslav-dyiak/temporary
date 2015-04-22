(function() {
    'use strict';

    app.controller('ManageCompanyOutletsController', ManageCompanyOutletsController);
    ManageCompanyOutletsController
        .$inject = [
        '$scope',
        'OutletsFactory'
    ];

    function ManageCompanyOutletsController($scope, OutletsFactory) {
        activate();

        function activate() {
            OutletsFactory.get({},
                function (data) {
                    $scope.outlets = data.outlets;
                }, function (e) {
                    console.error(e);
                });
        }

        $scope.itemsByPage = 5;
    }
})();
