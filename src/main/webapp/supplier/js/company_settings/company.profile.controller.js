(function () {
    'use strict';

    app.controller('CompanyProfileController', CompanyProfileController);

    CompanyProfileController
        .$inject = [
        '$scope',
        '$http',
        'toaster',
        'AuthServerProvider',
        'CompanyFactory',
        'FileUploadService'
    ];

    function CompanyProfileController($scope, $http, toaster, AuthServerProvider, CompanyFactory, FileUploadService) {
        var master = {};
        $scope.company = {};
        $scope.logo = {};
        $scope.mainPicture = {};
        $scope.companyPictures = [];
        $scope.selectedPicture = '';

        $scope.uploadLogo = uploadLogo;
        $scope.removeLogo = removeLogo;
        $scope.uploadMainPicture = uploadMainPicture;
        $scope.removeMainPicture = removeMainPicture;
        $scope.uploadCompanyPictures = uploadCompanyPictures;
        $scope.removeCompanyPicture = removeCompanyPicture;
        $scope.selectPicture = selectPicture;
        $scope.update = update;
        $scope.revert = revert;

        activate();

        function activate() {
            $scope.company = AuthServerProvider.currentUserCompany();
            if (!$scope.company.supplierDetails) {
                $scope.company.supplierDetails = {
                    pictures: []
                };
            }
            master = angular.copy(AuthServerProvider.currentUser().company);
        }

        function update() {
            CompanyFactory.update($scope.company,
                function (data) {
                    toaster.pop('success', 'Success', 'Company saved');
                    $http.get("/api/account")
                        .success(function (data) {
                            AuthServerProvider.setUser(data);
                        })
                        .error(function (data) {
                            console.log(data);
                        });

                }, function (e) {
                    toaster.pop('error', 'Error', 'Error on update');
                });
        }


        function uploadLogo(image) {
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

        function removeLogo() {
            $scope.company.logo = {
                title: 'logo_placeholder',
                url: '/logo_placeholder.png'
            };
        }

        function uploadMainPicture(image) {
            if ($scope.mainPicture && image.length > 0) {
                FileUploadService.uploadFileToUrl(image[0])
                    .then(function (response) {
                        $scope.company.supplierDetails.mainPicture = {
                            title: image[0].name,
                            url: response.data.path
                        };
                    });
            }
        }

        function removeMainPicture() {
            $scope.company.supplierDetails.mainPicture = {
                title: 'logo_placeholder',
                url: '/logo_placeholder.png'
            };
        }

        function uploadCompanyPictures(images) {
            if ($scope.companyPictures && images.length > 0) {
                for (var i = 0; i < images.length; i++) {
                    var name = images[i].name;
                    if ($scope.company.supplierDetails.pictures.length < 6) {
                        FileUploadService.uploadFileToUrl(images[i])
                            .then(function (response) {
                                var picture = {
                                    title: name,
                                    url: response.data.path
                                };
                                if (!$scope.company.supplierDetails.pictures) {
                                    $scope.company.supplierDetails.pictures = [];
                                }
                                $scope.company.supplierDetails.pictures.push(picture);
                            });
                    } else {
                        toaster.pop('error', 'Error', "Can't add more than 6 pictures");
                    }
                }
            }
        }

        function removeCompanyPicture() {
            for (var i = 0; i < $scope.company.supplierDetails.pictures.length; i++) {
                if ($scope.company.supplierDetails.pictures[i].url == $scope.selectedPicture) {
                    $scope.company.supplierDetails.pictures.splice(i, 1);
                    return;
                }
            }
        }

        function selectPicture(url) {
            if ($scope.selectedPicture == url) {
                $scope.selectedPicture = '';
            } else {
                $scope.selectedPicture = url;
            }
        }

        function revert() {
            $scope.company = master;
        }


    }
})();
