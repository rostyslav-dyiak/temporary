(function () {
    'use strict';

    app.controller('CompanyAddUpdateController', CompanyAddUpdateController);

    CompanyAddUpdateController
        .$inject = [
        '$scope',
        '$stateParams',
        '$http',
        'CompanyFactory',
        'BusinessTypeFactory',
        'FileUploadService'
    ];

    function CompanyAddUpdateController($scope, $stateParams, $http, CompanyFactory, BusinessTypeFactory, FileUploadService) {
        var companyId = $stateParams.id;

        var master = {};
        $scope.userCompanyDTO = {
            company: {}
        };
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
            BusinessTypeFactory.query({},
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
            var promiseFile = FileUploadService.uploadFileToUrl($scope.logo);
            promiseFile.then(function (response) {
                    var pictureObject = {
                        title: $scope.logo.name,
                        url: response.data.path
                    };
                    $scope.userCompanyDTO.logo = pictureObject;
                    if ($scope.userCompanyDTO.company.companyType != 'EATERY') {
                        $scope.userCompanyDTO.company.businessType = {};
                    }
                    $scope.userCompanyDTO.email = $scope.userCompanyDTO.company.email;
                    $scope.userCompanyDTO.role = "ROLE_" + $scope.userCompanyDTO.company.companyType.toUpperCase() + "_ADMIN";
                    $http.post('/api/invite', $scope.userCompanyDTO)
                        .success(function (data, status, headers) {
                            console.log(data + " " + status + " " + headers + " ");
                        }).
                        error(function (data, status, headers) {
                            console.log(data + " " + status + " " + headers + " ");
                        });
                }
            );
        }

        function save(company) {
            if (company.companyType != 'EATERY') {
                company.businessTypes = "";
            }
            CompanyFactory.update(company,
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
