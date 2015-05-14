(function () {
    'use strict';

    app.controller('CompanyAddUpdateController', CompanyAddUpdateController);

    CompanyAddUpdateController
        .$inject = [
        '$scope',
        '$stateParams',
        '$http',
        'toaster',
        'CompanyFactory',
        'BusinessTypeFactory',
        'InvitationHistoryFactory',
        'FileUploadService'
    ];

    function CompanyAddUpdateController($scope, $stateParams, $http, toaster, CompanyFactory, BusinessTypeFactory, InvitationHistoryFactory, FileUploadService) {
        var oldEmail = "";
        $scope.companyId = $stateParams.id;
        $scope.companyStatus = "";
        $scope.businessTypes = {};
        $scope.userCompanyDTO = {};
        $scope.invitationHistory = [];
        $scope.rowCollectionPage = [];

        $scope.removePhoto = removePhoto;
        $scope.addCompany = addCompany;
        $scope.uploadPhoto = uploadPhoto;
        $scope.changeBusinessType = changeBusinessType;
        $scope.deleteCompany = deleteCompany;
        $scope.updateCompanyInfo = updateCompanyInfo;


        activate();

        function activate() {
            if ($scope.companyId) {
                CompanyFactory.get({
                        id: $scope.companyId
                    },
                    function (data) {
                        $scope.companyStatus = data.status;
                        oldEmail = data.email;
                        $scope.userCompanyDTO.company = angular.copy(data);
                    }, function (e) {
                        console.error(e);
                    });
                InvitationHistoryFactory.query({
                    id: $scope.companyId
                }, function (data) {
                    $scope.invitationHistory = data;
                    $scope.rowCollectionPage = data;
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
            if (!$scope.companyId) {
                createCompany();
            } else {
                updateCompany();
            }

        }

        function createCompany() {
            $scope.userCompanyDTO.company.status = "PENDING";
            $scope.userCompanyDTO.email = $scope.userCompanyDTO.company.email;
            $scope.userCompanyDTO.role = "ROLE_" + $scope.userCompanyDTO.company.companyType.toUpperCase() + "_ADMIN";
            $http.post("/api/invite",
                $scope.userCompanyDTO)
                .success(function (data) {
                    console.log(data);
                    toaster.pop('success', 'Success', 'Company created');
                }).
                error(function (data) {
                    console.log(data);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }


        function updateCompany() {
            CompanyFactory.update($scope.userCompanyDTO.company,
                function (data) {
                    console.log('Saved ' + data.id);
                    toaster.pop('success', 'Success', 'Company updated');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }


        function deleteCompany() {
            CompanyFactory.delete(
                {id: $scope.companyId},
                function (data) {
                    console.log('Deleted ' + data.id)
                    toaster.pop('success', 'Success', 'Company deleted');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', "Company can't be removed");
                }
            )
            ;
        }

        function updateCompanyInfo() {
            $scope.userCompanyDTO.company.status = "PENDING";
            $scope.userCompanyDTO.email = oldEmail;
            $scope.userCompanyDTO.role = "ROLE_" + $scope.userCompanyDTO.company.companyType.toUpperCase() + "_ADMIN";
            $http.post("/api/invite/update",
                $scope.userCompanyDTO)
                .success(function (data) {
                    console.log(data);
                    toaster.pop('success', 'Success', 'Company info updated');
                }).
                error(function (data) {
                    console.log(data);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function changeBusinessType() {
            if ($scope.userCompanyDTO.company.companyType != 'EATERY') {
                delete $scope.userCompanyDTO.company["eateryDetails"];
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
