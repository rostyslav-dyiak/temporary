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
        $scope.businessTypes = {};
        $scope.pictureObject = {};
        $scope.userCompanyDTO = {
            company: {}
        };

        $scope.removeImage = removeImage;
        $scope.addCompany = addCompany;
        $scope.uploadPhoto = uploadPhoto;
        $scope.cancel = cancel;
        $scope.changeBusinessType = changeBusinessType;

        activate();

        function activate() {
            if (companyId) {
                CompanyFactory.get({
                        id: companyId
                    },
                    function (data) {
                        master = angular.copy(data);
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

        function removeImage() {
            $scope.company.logo = 'img/logo_placeholder.png';
        }

        function addCompany() {
            if (!companyId) {
                createCompany();
            } else {
                updateCompany();
            }

        }

        function cancel() {
            $scope.company = master;
            $scope.company.logo = 'img/logo_placeholder.png';
        }

        function createCompany() {
            $scope.userCompanyDTO.email = $scope.userCompanyDTO.company.email;
            $scope.userCompanyDTO.role = "ROLE_" + $scope.userCompanyDTO.company.companyType.toUpperCase() + "_ADMIN";
            $scope.userCompanyDTO.company.logo = $scope.pictureObject;
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
            $scope.userCompanyDTO.company.logo = $scope.pictureObject;
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

        function uploadPhoto() {
            //var promiseFile = FileUploadService.uploadFileToUrl($scope.logo);
            //promiseFile.then(function (response) {
            //    $scope.pictureObject = {
            //        title: $scope.logo.name,
            //        url: response.data.path
            //    };
            //});
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
