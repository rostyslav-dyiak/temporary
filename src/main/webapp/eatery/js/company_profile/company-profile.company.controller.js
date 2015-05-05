(function () {
    'use strict';

    app.controller('CompanyProfileCompanyController', CompanyProfileCompanyController);

    CompanyProfileCompanyController
        .$inject = [
        '$scope',
        'toaster',
        'AuthServerProvider',
        'CompanyFactory',
        'FileUploadService'
    ];

    function CompanyProfileCompanyController($scope, toaster, AuthServerProvider, CompanyFactory, FileUploadService) {
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
            AuthServerProvider.updateUserInfo()
                .then(function () {
                    $scope.company = angular.copy(AuthServerProvider.currentUser().company);
                },
                function (e) {
                    $scope.error = 'Please try again.';
                    console.error(e);
                });
        }

        function saveCompany() {
            CompanyFactory.update($scope.company,
                function (data) {
                    toaster.pop('success', 'Success', 'Company saved');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
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
