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

        $scope.businessTypes = {};
        $scope.userCompanyDTO = {
            company: {}
        };

        $scope.removePhoto = removePhoto;
        $scope.addCompany = addCompany;
        $scope.uploadPhoto = uploadPhoto;
        $scope.changeBusinessType = changeBusinessType;

        activate();

        function activate() {
            if (companyId) {
                CompanyFactory.get({
                        id: companyId
                    },
                    function (data) {
                        $scope.userCompanyDTO.company = angular.copy(data);
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

        function addCompany() {
            if (!companyId) {
                createCompany();
            } else {
                updateCompany();
            }

        }

        function createCompany() {
            $scope.userCompanyDTO.email = $scope.userCompanyDTO.company.email;
            $scope.userCompanyDTO.role = "ROLE_" + $scope.userCompanyDTO.company.companyType.toUpperCase() + "_ADMIN";
            $http.post("/api/invite",
                $scope.userCompanyDTO)
                .success(function (data) {
                    console.log(data)
                }).
                error(function (data) {
                    console.log(data)
                });
        }

        function updateCompany() {
            CompanyFactory.update($scope.userCompanyDTO.company,
                function (data) {
                    console.log('Saved ' + data.id)
                }, function (e) {
                    console.error(e);
                });
        }

        function changeBusinessType() {
            if ($scope.userCompanyDTO.company.companyType != 'EATERY') {
                delete $scope.userCompanyDTO.company["businessType"];
            }
        }

        function uploadPhoto(image) {
            if ($scope.logo && image.length > 0) {
                FileUploadService.uploadFileToUrl(image[0])
                    .then(function (response) {
                        $scope.userCompanyDTO.company.logo = {
                            title: image[0].name,
                            url: response.data.path
                        };
                    });
            }
        }

        function removePhoto() {
            $scope.userCompanyDTO.company.logo = {
                title: 'logo_placeholder',
                url: '/logo_placeholder.png'
            };
        }

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
    }
})();
