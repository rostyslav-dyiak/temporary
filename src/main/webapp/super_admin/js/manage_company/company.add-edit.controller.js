(function () {
    'use strict';

    app.controller('CompanyAddUpdateController', CompanyAddUpdateController);

    CompanyAddUpdateController
        .$inject = [
        '$scope',
        '$stateParams',
        '$http',
        'CompanyFactory',
        'BusinessTypeFactory'
    ];

    function CompanyAddUpdateController($scope, $stateParams, $http, CompanyFactory, BusinessTypeFactory) {
        var companyId = $stateParams.id;

        var master = {};
        $scope.company = {};
        $scope.businessTypes = {};
        $scope.invitationHistory = [{
            id: 0,
            email: 'one@test.com',
            sent: new Date('10/03/2015'),
            status: 'Expired'
        }, {
            id: 1,
            email: 'two@test.com',
            sent: new Date('02/12/2013'),
            status: 'Expired'
        }, {
            id: 2,
            email: 'three@test.com',
            sent: new Date('10/03/2015'),
            status: 'Signed up' + new Date('10/03/2015')
        }];

        $scope.removeImage = removeImage;
        $scope.addCompany = addCompany;
        $scope.save = save;
        $scope.cancel = cancel;

        activate();

        function activate() {
            if (companyId) {
                CompanyFactory.get({
                        id: companyId
                    },
                    function (data) {
                        master = angular.copy(data);
                        $scope.company = angular.copy(data);
                    }, function (e) {
                        console.error(e);
                    });
            }
            BusinessTypeFactory.get({},
                function (data) {
                    $scope.businessTypes = angular.copy(data);
                }, function (e) {
                    console.error(e);
                });
        }

        function removeImage() {
            $scope.company.logo = 'img/logo_placeholder.png';
        }

        function addCompany() {
            $http.post('/api/register', $scope.company)
                .success(function (data, status, headers, config) {
                    console.log(data + " " + status + " " + headers + " ");
                }).
                error(function (data, status, headers, config) {
                    console.log(data + " " + status + " " + headers + " ");
                });
        }

        function save(company) {
            CompanyFactory.save(company,
                function (data) {
                    console.log('Saved ' + data.id)
                }, function (e) {
                    console.error(e);
                });
        }

        function cancel() {
            $scope.company = master;
            $scope.company.logo = 'img/logo_placeholder.png';
        }
    }
})();
