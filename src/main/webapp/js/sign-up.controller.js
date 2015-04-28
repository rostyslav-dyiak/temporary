(function () {
    'use strict';

    app.controller('SignUpController', SignUpController);

    SignUpController
        .$inject = [
        '$scope',
        '$http',
        '$location'
    ];

    function SignUpController($scope, $http, $location) {
        $scope.user = {};
        $scope.authError = '';

        $scope.expired = false;
        $scope.created = false;

        $scope.signup = signup;

        activate();

        function activate() {
            $http.get('/api/activate', {
                params: {
                    key: $location.search().key
                }
            }).error(function (e) {
                $scope.expired = false;
                console.error(e);
            });
        }

        function signup(user) {
            //UserFactory.save(user,
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
                role: "ROLE_SUPER_ADMIN"
            }
        }
    }
})();
