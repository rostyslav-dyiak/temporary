(function() {
    'use strict';

    app.controller('SignUpController', SignUpController);

    SignUpController
        .$inject = [
        '$scope',
        '$http',
        '$location',
        'UserFactory'
    ];

    function SignUpController($scope, $http, $location, UserFactory) {
        $scope.user = {};

        $scope.expired = false;
        $scope.created = false;

        $scope.signup = signup;

        activate();

        function activate() {
            $http.post('/api/check', {key: $location.search().key})
                .success(function(data) {
                    $scope.expired = data;
                })
                .error(function(e) {
                    console.error(e);
                });
        }

        function signup() {
            //UserFactory.save($scope.user,
            //function(data) {
            //    $scope.created = true;
            //    $scope.user = data;
            //},
            //function(e) {
            //    $scope.authError = "Check your data";
            //    console.error(e);
            //});

            console.log("Save");
            $scope.created = true;
            $scope.user = {
                id: 1,
                role: "Supplier"
            }
        }
    }
})();
