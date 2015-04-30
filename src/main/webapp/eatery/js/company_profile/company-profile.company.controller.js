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

        $scope.save = save;
        $scope.removeLogo = removeLogo;
        $scope.removeOptionalImage = removeOptionalImage;
        $scope.upload = upload;

        activate();

        function activate() {
            $scope.company = AuthServerProvider.currentUserCompany();
        }

        function save() {
            CompanyFactory.update($scope.company,
                function (data) {
                    activate();
                }, function (e) {
                    console.error(e);
                });
            console.log("Saved type with id: " + id);
        }

        function removeLogo() {
            $scope.company.logo.url = 'placeholder.png';
            $scope.logo = {};
        }

        function removeOptionalImage() {
            $scope.company.optionalImage.url = 'placeholder.png'
            $scope.optionalImage = {};
        }

        function upload(image) {
            console.log(image);
            if ($scope.logo && image.length > 0) {
                FileUploadService.uploadFileToUrl(image[0])
                    .then(function (response) {
                        $scope.pictureObject = {
                            title: $scope.logo.name,
                            url: response.data.path
                        };
                    });
            }
        }
    }
})();
