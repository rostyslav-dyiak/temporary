(function () {
    'use strict';

    app.controller('ManageCompanyAddUpdateController', ManageCompanyAddUpdateController);

    ManageCompanyAddUpdateController
        .$inject = [
        '$scope',
        '$stateParams',
        '$http'
    ];

    function ManageCompanyAddUpdateController($scope, $stateParams, $http) {
        var companyId = $stateParams.id;
        $scope.company = {};
        $scope.removeImage = removeImage;

        $scope.addCompany =addCompany;
        if (companyId == 0) {
            $scope.company = {
                id: 3,
                code: 'FX',
                logo: 'https://www.edubuntu.org/sites/default/files/images/ubuntu-logo-only_32.png',
                email: 'just@test.com',
                name: 'FoodService',
                type: 'Supplier',
                created: new Date('10/03/2015'),
                contactNumber: '233-425-123',
                status: 'Active'
            }
        } else if (companyId == 1) {
            $scope.company = {
                id: 1,
                code: 'MB',
                logo: 'https://www.edubuntu.org/sites/default/files/images/ubuntu-logo-only_32.png',
                email: 'just@test.com',
                name: 'Mr Bean',
                type: 'Eatery',
                businessType: 'Cafe',
                created: new Date('02/12/2013'),
                contactNumber: '233-425-123',
                status: 'Active'
            }
        } else if (companyId == 2) {
            $scope.company = {
                id: 2,
                code: 'CJ',
                logo: 'https://www.edubuntu.org/sites/default/files/images/ubuntu-logo-only_32.png',
                email: 'just@test.com',
                name: 'Cafe J',
                type: 'Eatery',
                businessType: 'Canteen',
                created: new Date('10/03/2015'),
                contactNumber: '233-425-123',
                status: 'Inactive'
            }
        }

        if(companyId) {
            $scope.rowCollection = [{
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
        };
    }
})
();
