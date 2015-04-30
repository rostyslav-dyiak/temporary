(function () {
    'use strict';

    app.controller('CompanyProfileCompanyController', CompanyProfileCompanyController);

    CompanyProfileCompanyController
        .$inject = [
        '$scope',
        'AuthServerProvider',
        'CompanyFactory',
        'FileUploadService'
    ];

    function CompanyProfileCompanyController($scope, AuthServerProvider, CompanyFactory, FileUploadService) {
        $scope.company = {};
        $scope.logo = {};
        $scope.optionalImage = {};

        $scope.saveCompany = saveCompany;
        $scope.uploadPhoto = uploadPhoto;
        $scope.uploadOptionalImage = uploadOptionalImage;
        $scope.removePhoto = removePhoto;
        $scope.removeOptionalImage = removeOptionalImage;

        activate();

        function activate() {
            $scope.company = AuthServerProvider.currentUserCompany();
            console.log($scope.company);
        }

        function saveCompany() {
            CompanyFactory.update($scope.company,
                function (data) {
                    activate();
                }, function (e) {
                    console.error(e);
                });
        }

        function uploadPhoto(image) {
            if ($scope.logo && image.length > 0) {
                FileUploadService.uploadFileToUrl(image[0])
                    .then(function (response) {
                        $scope.company.logo = {
                            title: image[0].name,
                            url: response.data.path
                        };
                    });
            }
        }

        function removePhoto() {
            $scope.company.logo = {
                title: 'logo_placeholder',
                url: '/logo_placeholder.png'
            };
        }

        function uploadOptionalImage(image) {
            if ($scope.optionalImage && image.length > 0) {
                FileUploadService.uploadFileToUrl(image[0])
                    .then(function (response) {
                        $scope.company.eateryDetails.topRightPicture = {
                            title: image[0].name,
                            url: response.data.path
                        };
                    });
            }
        }

        function removeOptionalImage() {
            $scope.company.eateryDetails.topRightPicture = {
                title: 'logo_placeholder',
                url: '/logo_placeholder.png'
            };
        }
    }
})();
