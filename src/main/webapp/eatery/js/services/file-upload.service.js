(function () {
    'use strict';

    angular
        .module('app')
        .service('FileUploadService', FileUploadService);

    FileUploadService
        .$inject = [
        '$http'
    ];

    function FileUploadService($http) {
        var uploadUrl = "/api/content";

        this.uploadFileToUrl = function (file) {
            var fd = new FormData();
            fd.append('content', file);
            return $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {
                    'content': 'file',
                    'Content-Type': undefined
                }
            });
        };
    }

})();
